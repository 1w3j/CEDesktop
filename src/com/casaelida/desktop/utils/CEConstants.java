package com.casaelida.desktop.utils;

import com.casaelida.desktop.CasaElidaDesktopApp;
import com.casaelida.desktop.controllers.AppController;
import com.casaelida.desktop.controllers.login.LoginController;
import com.casaelida.desktop.controllers.login.LoginPasswordStepController;
import com.casaelida.desktop.controllers.login.LoginUserEmailStepController;
import com.casaelida.desktop.controllers.main.MainController;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import io.datafx.core.concurrent.ObservableExecutor;
import java.io.InputStream;
import java.net.URI;
import java.util.ResourceBundle;
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
    public static interface CasaElida{
        public static final ObservableExecutor POOL = ObservableExecutor.getDefaultInstance();
        public static final Class<?> CLASS = CasaElidaDesktopApp.class;
        public static final Logger LOGGER = Logger.getLogger(CLASS.getName());
        public static final String STAGE = "CASAELIDA_STAGE";
        public static final Double MIN_WIDTH = 600d;
        public static final Double MIN_HEIGHT = 700d;
        public static void l(String s){System.out.println(s);}
        public static interface App{
            public static final Class<?> CLASS = AppController.class;
            public static final Logger LOGGER = Logger.getLogger(CLASS.getName());
            public static final Double TOOLBAR_HEIGHT = 62.5d;
            public static final String TOOLBAR = "toolbar";
            public static final String BTN_TOOLBAR_SIDEMENU_BURGER = "btn-toolbar-sidemenu-burger";
            public static final String LBL_TOOLBAR = "lbl-toolbar";
            public static final String BTN_TOOLBAR_OPTIONS_BURGER = "btn-toolbar-options-burger";
            public static final String DRAWER = "app-drawer";
            public static interface Flow{
                public static final String ACTION_HANDLER = "APP_ACTION_HANDLER";
            }
            public static interface Strings{
                public static final String BUNDLE_BASE_NAME = "strings.app";
                public static final ResourceBundle BUNDLE = ResourceBundle.getBundle(BUNDLE_BASE_NAME);
                public static final String WINDOW_TITLE = BUNDLE.getString("window-title");
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
                    public static final String HANDLER = "LOGIN_FLOWHANDLER";
                    public static final String ACTION_HANDLER = "LOGIN_ACTION_HANDLER";
                }
                public static interface Strings{
                    public static final String BUNDLE_BASE_NAME = "strings.login.login";
                    public static final ResourceBundle BUNDLE = ResourceBundle.getBundle(BUNDLE_BASE_NAME);
                    public static final String TITLE = BUNDLE.getString("title");
                    public static final String ANDROID_LINK_ICON_TOOLTIP = BUNDLE.getString("android-link-icon-tooltip");
                    public static final String WEB_LINK_ICON_TOOLTIP = BUNDLE.getString("web-link-icon-tooltip");
                    public static final String DESKTOP_LINK_ICON_TOOLTIP = BUNDLE.getString("desktop-link-icon-tooltip");
                    public static final String LOGIN_LOGO_PANE_CASA = BUNDLE.getString("login-logo-pane-casa");
                    public static final String LOGIN_LOGO_PANE_ELIDA = BUNDLE.getString("login-logo-pane-elida");
                    public static final String DESKTOP_VERSION_TEXT_FIRST = BUNDLE.getString("desktop-version-text-first");
                    public static final String DESKTOP_VERSION_TEXT_SECOND = BUNDLE.getString("desktop-version-text-second");
                }
                public static interface Steps{
                    public static interface UserEmail{
                        public static final Class<?> CLASS = LoginUserEmailStepController.class;
                        public static final Logger LOGGER = Logger.getLogger(CLASS.getName());
                        public static final String PANE = "auth-useremail-step-pane";
                        public static final String PANE_HEADER = "auth-useremail-step-pane-header";
                        public static final String TXT_USEREMAIL = "txt-useremail";
                        public static final String BTN_NEXT = "btn-next";
                        public static final MaterialIconView WARNING_ICON = new MaterialIconView(MaterialIcon.WARNING, "22.5");
                        public static interface Flow{
                            public static final String VALIDATE = "VALIDATE_USEREMAIL";
                        }
                        public static interface Strings{
                            public static final String BUNDLE_BASE_NAME = "strings.login.steps";
                            public static final ResourceBundle BUNDLE = ResourceBundle.getBundle(BUNDLE_BASE_NAME);
                            public static final String USEREMAIL_TOOLTIP_EMPTY = BUNDLE.getString("useremail-tooltip-empty");
                            public static final String USEREMAIL_TOOLTIP_NONEMPTY = BUNDLE.getString("useremail-tooltip-nonempty");
                            public static final String REQUIRED_EMAIL_MESSAGE = BUNDLE.getString("required-email-message");
                            public static final String INVALID_EMAIL_MESSAGE = BUNDLE.getString("invalid-email-message");
                            public static final String USEREMAIL_PANE_HEADER_LOGIN = BUNDLE.getString("useremail-pane-header-login");
                            public static final String USEREMAIL_PANE_SUBHEADER_TOCONTINUE = BUNDLE.getString("useremail-pane-subheader-tocontinue");
                            public static final String TXT_USEREMAIL_PROMPT_TEXT = BUNDLE.getString("txt-useremail-prompt-text");
                            public static final String BTN_NEXT = BUNDLE.getString("btn-next");
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
                        public static final MaterialIconView WARNING_ICON = new MaterialIconView(MaterialIcon.WARNING, "22.5");
                        public static interface Flow{
                            public static final String VALIDATE = "VALIDATE_USER_PASSWORD";
                        }
                        public static interface Strings{
                            public static final String BUNDLE_BASE_NAME = "strings.login.steps";
                            public static final ResourceBundle BUNDLE = ResourceBundle.getBundle(BUNDLE_BASE_NAME);
                            public static final String BTN_CHANGE_USER_TOOLTIP = BUNDLE.getString("btn-change-user-tooltip");
                            public static final String BTN_LOGIN = BUNDLE.getString("btn-login");
                            public static final String REQUIRED_PASSWORD_MESSAGE = BUNDLE.getString("required-password-message");
                            public static final String TXT_PASSWORD_PROMPT_TEXT = BUNDLE.getString("txt-password-prompt-text");
                            public static final String PASSWORD_PANE_HEADER_WELCOME = BUNDLE.getString("password-pane-header-welcome");
                        }
                    }
                }
            }
            public static interface Main{
                public static final Class<?> CLASS = MainController.class;
                public static final Logger LOGGER = Logger.getLogger(CLASS.getName());
                public static interface Flow{

                }
                public static interface Strings{
                    public static final String BUNDLE_BASE_NAME = "strings.main.main";
                    public static final ResourceBundle BUNDLE = ResourceBundle.getBundle(BUNDLE_BASE_NAME);
                    public static final String TITLE = BUNDLE.getString("title");
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
    }
    public static interface Database{
        public static final String HOST = "localhost";
        public static final int PORT = 3306;
        public static final String NAME = "casaelida";
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
            public static final String APP_STYLESHEET = STYLESHEETS_PATH + "app.css";
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
            public static final InputStream ROBOTO_BOLD_STREAM = CasaElida.App.CLASS.getResourceAsStream(ROBOTO_BOLD);
            public static final InputStream ROBOTO_BLACK_STREAM = CasaElida.App.CLASS.getResourceAsStream(ROBOTO_BLACK);
            public static final InputStream ROBOTO_BOLD_ITALIC_STREAM = CasaElida.App.CLASS.getResourceAsStream(ROBOTO_BOLD_ITALIC);
            public static final InputStream ROBOTO_LIGHT_STREAM = CasaElida.App.CLASS.getResourceAsStream(ROBOTO_LIGHT);
            public static final InputStream ROBOTO_MEDIUM_STREAM = CasaElida.App.CLASS.getResourceAsStream(ROBOTO_MEDIUM);
            public static final InputStream ROBOTO_REGULAR_STREAM = CasaElida.App.CLASS.getResourceAsStream(ROBOTO_REGULAR);
        }
    }
}
