package com.casaelida.desktop.utils.datafx;

import com.casaelida.desktop.utils.CEConstants.CasaElida.App;
import io.datafx.controller.context.ViewContext;
import io.datafx.controller.flow.FlowContainer;
import io.datafx.controller.flow.container.AnimatedFlowContainer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.List;
import java.util.function.Function;

/**
 * @author iqbal
 * <p>
 * A {@link FlowContainer} that supports animation for the view change of the Casa Elida Desktop App.
 */
public class CEAnimatedFlowContainer extends AnimatedFlowContainer implements FlowContainer<StackPane> {

    private final StackPane view;
    private final Duration duration;
    private final ImageView placeholder;
    private Function<CEAnimatedFlowContainer, List<KeyFrame>> animationProducer;
    private Timeline animation;

    public CEAnimatedFlowContainer () {
        this.view = new StackPane();
        this.duration = App.Animations.MaterialDesign.DURATION;
        this.animationProducer = null;
        this.placeholder = new ImageView();
        this.placeholder.setPreserveRatio(true);
        this.placeholder.setSmooth(true);
    }

    private void changeAnimation (CEContainerAnimations animation) {
        this.animationProducer = animation.getAnimationProducer();
    }

    @Override public <U> void setViewContext (ViewContext<U> context) {
        switch ((App.Animations) context.getApplicationContext().getRegisteredObject(App.Animations.Flow.NEXT_ANIMATION)) {
            case APP_START:
                changeAnimation(CEContainerAnimations.ZOOM_IN);
                break;
            case LOGIN_NEXT:
                changeAnimation(CEContainerAnimations.SWIPE_LEFT);
                break;
            case LOGIN_BACK:
                changeAnimation(CEContainerAnimations.SWIPE_RIGHT);
                break;
            case MAIN_START:
                changeAnimation(CEContainerAnimations.FADE);
                break;
            case SIDEMENU_OPEN:
                changeAnimation(CEContainerAnimations.SWIPE_RIGHT);
                break;
            case LOG_OUT:
                changeAnimation(CEContainerAnimations.SWIPE_LEFT);//error here, never ZOOMs OUT
                break;
            default:
                changeAnimation(CEContainerAnimations.ZOOM_OUT);
        }
        updatePlaceholder(context.getRootNode());
        if (this.animation != null) {
            this.animation.stop();
        }
        this.animation = new Timeline();
        this.animation.getKeyFrames().addAll(this.animationProducer.apply(this));
        this.animation.getKeyFrames().add(new KeyFrame(this.duration, (e) -> clearPlaceholder()));
        this.animation.play();
    }

    @Override public ImageView getPlaceholder () {
        return this.placeholder;
    }

    @Override public Duration getDuration () {
        return this.duration;
    }

    @Override public StackPane getView () {
        return this.view;
    }

    private void clearPlaceholder () {
        this.view.getChildren().remove(this.placeholder);
    }

    private void updatePlaceholder (Node newView) {
        if (this.view.getWidth() > 0 && this.view.getHeight() > 0) {
            SnapshotParameters parameters = new SnapshotParameters();
            parameters.setFill(Color.TRANSPARENT);
            Image placeholderImage = this.view.snapshot(parameters, new WritableImage((int) this.view.getWidth(), (int) this.view.getHeight()));
            this.placeholder.setImage(placeholderImage);
            this.placeholder.setFitWidth(placeholderImage.getWidth());
            this.placeholder.setFitHeight(placeholderImage.getHeight());
        } else {
            this.placeholder.setImage(null);
        }
        this.placeholder.setVisible(true);
        this.placeholder.setOpacity(1.0);
        this.view.getChildren().setAll(this.placeholder, newView);
        this.placeholder.toFront();
    }
}
