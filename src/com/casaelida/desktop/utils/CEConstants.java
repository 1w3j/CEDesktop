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
    interface CasaElida{
        ObservableExecutor POOL = ObservableExecutor.getDefaultInstance();
        Class<?> CLASS = CasaElidaDesktopApp.class;
        Logger LOGGER = Logger.getLogger(CLASS.getName());
        String STAGE = "CASAELIDA_STAGE";
        Double MIN_WIDTH = 600d;
        Double MIN_HEIGHT = 700d;
        static void l(String s){System.out.println(s);}
        interface App{
            Class<?> CLASS = AppController.class;
            Logger LOGGER = Logger.getLogger(CLASS.getName());
            Double TOOLBAR_HEIGHT = 62.5d;
            String TOOLBAR = "toolbar";
            String BTN_TOOLBAR_SIDEMENU_BURGER = "btn-toolbar-sidemenu-burger";
            String LBL_TOOLBAR = "lbl-toolbar";
            String BTN_TOOLBAR_OPTIONS_BURGER = "btn-toolbar-options-burger";
            String DRAWER = "app-drawer";
            interface Flow{
                String ACTION_HANDLER = "APP_ACTION_HANDLER";
            }
            interface Strings{
                String BUNDLE_BASE_NAME = "strings.app";
                ResourceBundle BUNDLE = ResourceBundle.getBundle(BUNDLE_BASE_NAME);
                String WINDOW_TITLE = BUNDLE.getString("window-title");
            }
            interface Login{
                Class<?> CLASS = LoginController.class;
                Logger LOGGER = Logger.getLogger(CLASS.getName());
                String PANE_WRAPPER = "login-pane-wrapper";
                String PANE = "login-pane";
                String TITLE_PANE = "login-title-pane";
                String LOGO_PANE = "login-logo-pane";
                String LOGO_PANE_CASA = "login-logo-pane-casa";
                String LOGO_PANE_ELIDA = "login-logo-pane-elida";
                String APP_LINKS_PANE = "login-app-links-pane";
                String ANDROID_LINK_ICON = "android-link-icon";
                String WEB_LINK_ICON = "web-link-icon";
                String DESKTOP_LINK_ICON = "desktop-link-icon";
                String AUTH_PANE = "auth-pane";
                interface Flow{
                    String HANDLER = "LOGIN_FLOWHANDLER";
                    String ACTION_HANDLER = "LOGIN_ACTION_HANDLER";
                }
                interface Strings{
                    String BUNDLE_BASE_NAME = "strings.login.login";
                    ResourceBundle BUNDLE = ResourceBundle.getBundle(BUNDLE_BASE_NAME);
                    String TITLE = BUNDLE.getString("title");
                    String ANDROID_LINK_ICON_TOOLTIP = BUNDLE.getString("android-link-icon-tooltip");
                    String WEB_LINK_ICON_TOOLTIP = BUNDLE.getString("web-link-icon-tooltip");
                    String DESKTOP_LINK_ICON_TOOLTIP = BUNDLE.getString("desktop-link-icon-tooltip");
                    String LOGIN_LOGO_PANE_CASA = BUNDLE.getString("login-logo-pane-casa");
                    String LOGIN_LOGO_PANE_ELIDA = BUNDLE.getString("login-logo-pane-elida");
                    String DESKTOP_VERSION_TEXT_FIRST = BUNDLE.getString("desktop-version-text-first");
                    String DESKTOP_VERSION_TEXT_SECOND = BUNDLE.getString("desktop-version-text-second");
                }
                interface Steps{
                    interface UserEmail{
                        Class<?> CLASS = LoginUserEmailStepController.class;
                        Logger LOGGER = Logger.getLogger(CLASS.getName());
                        String PANE = "auth-useremail-step-pane";
                        String PANE_HEADER = "auth-useremail-step-pane-header";
                        String TXT_USEREMAIL = "txt-useremail";
                        String BTN_NEXT = "btn-next";
                        MaterialIconView WARNING_ICON = new MaterialIconView(MaterialIcon.WARNING, "22.5");
                        interface Flow{
                            String VALIDATE = "VALIDATE_USEREMAIL";
                        }
                        interface Strings{
                            String BUNDLE_BASE_NAME = "strings.login.steps";
                            ResourceBundle BUNDLE = ResourceBundle.getBundle(BUNDLE_BASE_NAME);
                            String USEREMAIL_TOOLTIP_EMPTY = BUNDLE.getString("useremail-tooltip-empty");
                            String USEREMAIL_TOOLTIP_NONEMPTY = BUNDLE.getString("useremail-tooltip-nonempty");
                            String REQUIRED_EMAIL_MESSAGE = BUNDLE.getString("required-email-message");
                            String INVALID_EMAIL_MESSAGE = BUNDLE.getString("invalid-email-message");
                            String USEREMAIL_PANE_HEADER_LOGIN = BUNDLE.getString("useremail-pane-header-login");
                            String USEREMAIL_PANE_SUBHEADER_TOCONTINUE = BUNDLE.getString("useremail-pane-subheader-tocontinue");
                            String TXT_USEREMAIL_PROMPT_TEXT = BUNDLE.getString("txt-useremail-prompt-text");
                            String BTN_NEXT = BUNDLE.getString("btn-next");
                        }
                    }
                    interface Password{
                        Class<?> CLASS = LoginPasswordStepController.class;
                        Logger LOGGER = Logger.getLogger(CLASS.getName());
                        String PANE = "auth-password-step-pane";
                        String PANE_HEADER = "auth-password-step-pane-header";
                        String LBL_USEREMAIL = "lbl-useremail";
                        String BTN_CHANGE_USER = "btn-change-user";
                        String BTN_LOGIN = "btn-login";
                        String TXT_PASSWORD = "txt-password";
                        String LBL_USERNAME = "lbl-username";
                        MaterialIconView WARNING_ICON = new MaterialIconView(MaterialIcon.WARNING, "22.5");
                        interface Flow{
                            String VALIDATE = "VALIDATE_USER_PASSWORD";
                        }
                        interface Strings{
                            String BUNDLE_BASE_NAME = "strings.login.steps";
                            ResourceBundle BUNDLE = ResourceBundle.getBundle(BUNDLE_BASE_NAME);
                            String BTN_CHANGE_USER_TOOLTIP = BUNDLE.getString("btn-change-user-tooltip");
                            String BTN_LOGIN = BUNDLE.getString("btn-login");
                            String REQUIRED_PASSWORD_MESSAGE = BUNDLE.getString("required-password-message");
                            String TXT_PASSWORD_PROMPT_TEXT = BUNDLE.getString("txt-password-prompt-text");
                            String PASSWORD_PANE_HEADER_WELCOME = BUNDLE.getString("password-pane-header-welcome");
                        }
                    }
                }
            }
            interface Main{
                Class<?> CLASS = MainController.class;
                Logger LOGGER = Logger.getLogger(CLASS.getName());
                interface Flow{

                }
                interface Strings{
                    String BUNDLE_BASE_NAME = "strings.main.main";
                    ResourceBundle BUNDLE = ResourceBundle.getBundle(BUNDLE_BASE_NAME);
                    String TITLE = BUNDLE.getString("title");
                }
            }
            enum Animations{
                APP_START, LOGIN_NEXT, LOGIN_BACK, MAIN_START;
                public interface Flow{
                    String NEXT_ANIMATION = "NEXT_ANIMATION";
                }
                public interface MaterialDesign{
                    Interpolator INTERPOLATOR = Interpolator.SPLINE(0.4, 0.0, 0.2, 1);
                    Duration DURATION = Duration.millis(270);
                }
            }
        }
    }
    interface Database{
        String HOST = "localhost";
        int PORT = 3306;
        String NAME = "casaelida";
        String CHARSET = "utf8mb4";
        String USER = "root";
        String PASSWORD = "12345678";
    }
    interface Meta{
        interface Icons{
            Image ICON16X16 = new Image("/images/logo-16x16.png");
            Image ICON28X28 = new Image("/images/logo-28x28.png");
            Image ICON56X56 = new Image("/images/logo-56x56.png");
            Image ICON64X64 = new Image("/images/logo-64x64.png");
            Image ICON128X128 = new Image("/images/logo-128x128.png");
            Image ICON256X256 = new Image("/images/logo-128x128.png");
        }
        interface Links{
            URI WEBAPP_URI = URI.create("http://iqbal98.netne.net");
        }
        interface Colors{
            Color RED = Color.web("#e74c3c");
            Color RED_HOVER = Color.web("#e53f2e");
            Color YELLOW = Color.web("#f1c40f");
            Color YELLOW_HOVER = Color.web("#ffcc00");
            Color WHITE = Color.web("#ecf0f1");
            Color BLACK = Color.web("#333333");
            Color BACKGROUND = Color.web("#dddddd");
            Color TEXT_COLOR = Color.web("#595959");
            Color BORDER_COLOR = Color.web("#b1b1b1");
        }
        interface Stylesheets{
            String STYLESHEETS_PATH = "/css/";
            String VARIABLES_STYLESHEET = STYLESHEETS_PATH + "variables.css";
            String APP_STYLESHEET = STYLESHEETS_PATH + "app.css";
            String LOGIN_STYLESHEET = STYLESHEETS_PATH + "login.css";
            String MAIN_STYLESHEET = STYLESHEETS_PATH + "main.css";
        }
        interface Fonts{
            String FONTS_PATH = "/fonts/";
            String ROBOTO_BOLD = FONTS_PATH + "Roboto-Bold.ttf";
            String ROBOTO_BLACK = FONTS_PATH + "Roboto-Black.ttf";
            String ROBOTO_BOLD_ITALIC = FONTS_PATH + "Roboto-BoldItalic.ttf";
            String ROBOTO_LIGHT = FONTS_PATH + "Roboto-Light.ttf";
            String ROBOTO_MEDIUM = FONTS_PATH + "Roboto-Medium.ttf";
            String ROBOTO_REGULAR = FONTS_PATH + "Roboto-Regular.ttf";
            InputStream ROBOTO_BOLD_STREAM = CasaElida.App.CLASS.getResourceAsStream(ROBOTO_BOLD);
            InputStream ROBOTO_BLACK_STREAM = CasaElida.App.CLASS.getResourceAsStream(ROBOTO_BLACK);
            InputStream ROBOTO_BOLD_ITALIC_STREAM = CasaElida.App.CLASS.getResourceAsStream(ROBOTO_BOLD_ITALIC);
            InputStream ROBOTO_LIGHT_STREAM = CasaElida.App.CLASS.getResourceAsStream(ROBOTO_LIGHT);
            InputStream ROBOTO_MEDIUM_STREAM = CasaElida.App.CLASS.getResourceAsStream(ROBOTO_MEDIUM);
            InputStream ROBOTO_REGULAR_STREAM = CasaElida.App.CLASS.getResourceAsStream(ROBOTO_REGULAR);
        }
    }
}
