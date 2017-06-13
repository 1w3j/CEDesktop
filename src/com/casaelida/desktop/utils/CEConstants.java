package com.casaelida.desktop.utils;

import com.casaelida.desktop.CasaElidaDesktopApp;
import com.casaelida.desktop.controllers.login.LoginController;
import com.casaelida.desktop.controllers.login.LoginPasswordStepController;
import com.casaelida.desktop.controllers.login.LoginUserEmailStepController;
import com.casaelida.desktop.controllers.main.MainController;
import io.datafx.core.concurrent.ObservableExecutor;
import java.io.InputStream;
import java.net.URI;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author iqbal
 */
public interface CEConstants {
    public static interface App{
        public static final ObservableExecutor POOL = ObservableExecutor.getDefaultInstance();
        public static final Class<?> CLASS = CasaElidaDesktopApp.class;
        public static final Logger LOGGER = Logger.getLogger(CLASS.getName());
        public static final String STAGE = "CASAELIDA_STAGE";
        public static interface Flow{
            public static final String FLOW = "CASAELIDA_FLOW";
            public static final String FLOW_HANDLER = "CASAELIDA_FLOWHANDLER";
        }
        public static interface Login{
            public static final Class<?> CLASS = LoginController.class;
            public static final Logger LOGGER = Logger.getLogger(CLASS.getName());
            public static final String PANE_WRAPPER = "login-pane-wrapper";
            public static final String PANE = "login-pane";
            public static final String TITLE_PANE = "login-title-pane";
            public static final String LOGO_PANE = "login-logo-pane";
            public static final String LOGO_PANE_CASA = "login-logo-pane-casa";
            public static final String LOGO_PANE_ELIDA = "login-logo-pane-elida";
            public static final String APP_LINKS_PANE = "login-app-links-pane";
            public static final String ANDROID_LINK_ICON = "android-link-icon";
            public static final String WEB_LINK_ICON = "web-link-icon";
            public static final String DESKTOP_LINK_ICON = "desktop-link-icon";
            public static final String AUTH_PANE = "auth-pane";
            public static interface Flow{
                public static final String FLOW_HANDLER = "LOGIN_FLOWHANDLER";
            }
            public static interface Steps{
                public static interface Flow{
                    public static final String ACTION_HANDLER = "AUTH_STEPS_ACTION_HANDLER";
                }
                public static interface UserEmail{
                    public static final Class<?> CLASS = LoginUserEmailStepController.class;
                    public static final Logger LOGGER = Logger.getLogger(CLASS.getName());
                    public static final String PANE = "auth-useremail-step-pane";
                    public static final String PANE_HEADER = "auth-useremail-step-pane-header";
                    public static final String TXT_USEREMAIL = "txt-useremail";
                    public static final String BTN_NEXT = "btn-next";
                    public static interface Flow{
                        public static final String VALIDATE = "VALIDATE_USEREMAIL";
                    }
                }
                public static interface Password{
                    public static final Class<?> CLASS = LoginPasswordStepController.class;
                    public static final Logger LOGGER = Logger.getLogger(CLASS.getName());
                    public static final String PANE = "auth-password-step-pane";
                    public static final String PANE_HEADER = "auth-password-step-pane-header";
                    public static final String LBL_USEREMAIL = "lbl-useremail";
                    public static final String BTN_CHANGE_USER = "btn-change-user";
                    public static final String BTN_LOGIN = "btn-login";
                    public static final String TXT_PASSWORD = "txt-password";
                    public static final String LBL_USERNAME = "lbl-username";
                    public static interface Flow{
                        public static final String VALIDATE = "VALIDATE_USER_PASSWORD";
                    }
                }
            }
        }
        public static interface Main{
            public static final Class<?> CLASS = MainController.class;
            public static final Logger LOGGER = Logger.getLogger(CLASS.getName());
            public static interface Flow{
                
            }
        }
        public static enum Animations{
            APP_START, LOGIN_NEXT, LOGIN_BACK, MAIN_START;
            public static interface Flow{
                public static final String NEXT_ANIMATION = "NEXT_ANIMATION";
            }
            public static interface MaterialDesign{
                public static final Interpolator INTERPOLATOR = Interpolator.SPLINE(0.4, 0.0, 0.2, 1);
                public static final Duration DURATION = Duration.millis(270);
            }
        }
    }
    public static interface Db{
        public static final String HOST = "localhost";
        public static final int PORT = 3306;
        public static final String DATABASE = "casaelida";
        public static final String CHARSET = "utf8mb4";
        public static final String USER = "root";
        public static final String PASSWORD = "12345678";
    }
    public static interface Meta{
        public static interface Icons{
            public static final Image ICON16X16 = new Image("/images/logo-16x16.png");
            public static final Image ICON28X28 = new Image("/images/logo-28x28.png");
            public static final Image ICON56X56 = new Image("/images/logo-56x56.png");
            public static final Image ICON64X64 = new Image("/images/logo-64x64.png");
            public static final Image ICON128X128 = new Image("/images/logo-128x128.png");
            public static final Image ICON256X256 = new Image("/images/logo-128x128.png");
        }
        public static interface Links{
            public static final URI WEBAPP_URI = URI.create("http://iqbal98.netne.net");
        }
        public static interface Colors{
            public static final Color RED = CEFunctions.hex2Rgb("#e74c3c", 1.0);
            public static final Color RED_HOVER = CEFunctions.hex2Rgb("#e53f2e", 1.0);
            public static final Color YELLOW = CEFunctions.hex2Rgb("#f1c40f", 1.0);
            public static final Color YELLOW_HOVER = CEFunctions.hex2Rgb("#ffcc00", 1.0);
            public static final Color WHITE = CEFunctions.hex2Rgb("#ecf0f1", 1.0);
            public static final Color BLACK = CEFunctions.hex2Rgb("#333333", 1.0);
            public static final Color BACKGROUND = CEFunctions.hex2Rgb("#dddddd", 1.0);
            public static final Color TEXT_COLOR = CEFunctions.hex2Rgb("#595959", 1.0);
            public static final Color BORDER_COLOR = CEFunctions.hex2Rgb("#b1b1b1", 1.0);
        }
        public static interface Stylesheets{
            public static final String STYLESHEETS_PATH = "/css/";
            public static final String VARIABLES_STYLESHEET = STYLESHEETS_PATH + "variables.css";
            public static final String LOGIN_STYLESHEET = STYLESHEETS_PATH + "login.css";
            public static final String MAIN_STYLESHEET = STYLESHEETS_PATH + "main.css";
        }
        public static interface Fonts{
            public static final String FONTS_PATH = "/fonts/";
            public static final String ROBOTO_BOLD = FONTS_PATH + "Roboto-Bold.ttf";
            public static final String ROBOTO_BLACK = FONTS_PATH + "Roboto-Black.ttf";
            public static final String ROBOTO_BOLD_ITALIC = FONTS_PATH + "Roboto-BoldItalic.ttf";
            public static final String ROBOTO_LIGHT = FONTS_PATH + "Roboto-Light.ttf";
            public static final String ROBOTO_MEDIUM = FONTS_PATH + "Roboto-Medium.ttf";
            public static final String ROBOTO_REGULAR = FONTS_PATH + "Roboto-Regular.ttf";
            public static final InputStream ROBOTO_BOLD_STREAM = App.CLASS.getResourceAsStream(ROBOTO_BOLD);
            public static final InputStream ROBOTO_BLACK_STREAM = App.CLASS.getResourceAsStream(ROBOTO_BLACK);
            public static final InputStream ROBOTO_BOLD_ITALIC_STREAM = App.CLASS.getResourceAsStream(ROBOTO_BOLD_ITALIC);
            public static final InputStream ROBOTO_LIGHT_STREAM = App.CLASS.getResourceAsStream(ROBOTO_LIGHT);
            public static final InputStream ROBOTO_MEDIUM_STREAM = App.CLASS.getResourceAsStream(ROBOTO_MEDIUM);
            public static final InputStream ROBOTO_REGULAR_STREAM = App.CLASS.getResourceAsStream(ROBOTO_REGULAR);
        }
    }
}
