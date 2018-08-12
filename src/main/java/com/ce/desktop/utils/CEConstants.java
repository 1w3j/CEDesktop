package com.ce.desktop.utils;

import com.ce.desktop.CEDesktopApp;
import com.ce.desktop.controllers.AppController;
import com.ce.desktop.controllers.login.LoginController;
import com.ce.desktop.controllers.login.LoginPasswordStepController;
import com.ce.desktop.controllers.login.LoginSideMenuController;
import com.ce.desktop.controllers.login.LoginUserEmailStepController;
import com.ce.desktop.controllers.main.MainController;
import com.ce.desktop.controllers.main.MainSideMenuController;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import io.datafx.core.concurrent.ObservableExecutor;
import javafx.animation.Interpolator;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.InputStream;
import java.net.URI;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * @author 1w3j
 */
public interface CEConstants {
    interface CE {
        ObservableExecutor POOL = ObservableExecutor.getDefaultInstance();
        Class<?> CLASS = CEDesktopApp.class;
        Logger LOGGER = Logger.getLogger(CLASS.getName());
        String STAGE = "CE_STAGE";
        String SCENE = "CE_SCENE";
        Double MIN_WIDTH = 600d;
        Double MIN_HEIGHT = 700d;

        static void l (String s) {
            System.out.println(s);
        }

        interface App {
            Class<?> CLASS = AppController.class;
            Logger LOGGER = Logger.getLogger(CLASS.getName());
            Double TOOLBAR_HEIGHT = 62.5d;
            Double SIDEMENU_SIZE = 360d;
            String DRAWER = "app-drawer";
            String CONTAINER = "app-container";
            String TOOLBAR = "toolbar";
            String BTN_TOOLBAR_SIDEMENU_BURGER = "btn-toolbar-sidemenu-burger";
            String LBL_TOOLBAR = "lbl-toolbar";
            String BTN_TOOLBAR_OPTIONS_BURGER = "btn-toolbar-options-burger";
            String ROOT = "app-root";

            enum Animations {
                APP_START, LOGIN_NEXT, LOGIN_BACK, MAIN_START, SIDEMENU_OPEN, LOG_OUT;

                public interface Flow {
                    String NEXT_ANIMATION = "NEXT_ANIMATION";
                }

                public interface MaterialDesign {
                    Interpolator INTERPOLATOR = Interpolator.SPLINE(0.4, 0.0, 0.2, 1);
                    Duration DURATION = Duration.millis(270);
                }
            }

            interface Flow {
                String ACTION_HANDLER = "APP_ACTION_HANDLER";
            }

            interface Strings {
                String BUNDLE_BASE_NAME = "strings.app";
                ResourceBundle BUNDLE = ResourceBundle.getBundle(BUNDLE_BASE_NAME);
                String WINDOW_TITLE = BUNDLE.getString("window-title");
            }

            interface Login {
                Class<?> CLASS = LoginController.class;
                Logger LOGGER = Logger.getLogger(CLASS.getName());
                String PANE_WRAPPER = "login-pane-wrapper";
                String PANE = "login-pane";
                String TITLE_PANE = "login-title-pane";
                String LOGO_PANE = "login-logo-pane";
                String LOGO_TEXT_FIRST = "logo-text-first";
                String LOGO_TEXT_SECOND = "logo-text-second";
                String APP_LINKS_PANE = "login-app-links-pane";
                String ANDROID_LINK_ICON = "android-link-icon";
                String WEB_LINK_ICON = "web-link-icon";
                String DESKTOP_LINK_ICON = "desktop-link-icon";
                String AUTH_PANE = "auth-pane";

                interface Flow {
                    String HANDLER = "LOGIN_FLOWHANDLER";
                    String ACTION_HANDLER = "LOGIN_ACTION_HANDLER";
                }

                interface Strings {
                    String BUNDLE_BASE_NAME = "strings.login.login";
                    ResourceBundle BUNDLE = ResourceBundle.getBundle(BUNDLE_BASE_NAME);
                    String TITLE = BUNDLE.getString("title");
                    String ICON_TOOLTIP_ANDROID_LINK = BUNDLE.getString("icon-tooltip-android-link");
                    String ICON_TOOLTIP_WEB_LINK = BUNDLE.getString("icon-tooltip-web-link");
                    String ICON_TOOLTIP_DESKTOP_LINK = BUNDLE.getString("icon-tooltip-desktop-link");
                    String LOGO_TEXT_FIRST = BUNDLE.getString("logo-text-first");
                    String LOGO_TEXT_SECOND = BUNDLE.getString("logo-text-second");
                    String DESKTOP_VERSION_TEXT_FIRST = BUNDLE.getString("desktop-version-text-first");
                    String DESKTOP_VERSION_TEXT_SECOND = BUNDLE.getString("desktop-version-text-second");
                }

                interface Steps {
                    interface UserEmail {
                        Class<?> CLASS = LoginUserEmailStepController.class;
                        Logger LOGGER = Logger.getLogger(CLASS.getName());
                        String PANE = "auth-useremail-step-pane";
                        String PANE_HEADER = "auth-useremail-step-pane-header";
                        String TXT_USEREMAIL = "txt-useremail";
                        String BTN_NEXT = "btn-next";
                        MaterialIconView WARNING_ICON = new MaterialIconView(MaterialIcon.WARNING, "22.5");

                        interface Flow {
                            String VALIDATE = "VALIDATE_USEREMAIL";
                        }

                        interface Strings {
                            String BUNDLE_BASE_NAME = "strings.login.steps";
                            ResourceBundle BUNDLE = ResourceBundle.getBundle(BUNDLE_BASE_NAME);
                            String TOOLTIP_EMPTY = BUNDLE.getString("ue-tooltip-empty");
                            String TOOLTIP_NONEMPTY = BUNDLE.getString("ue-tooltip-nonempty");
                            String ERROR_REQUIRED = BUNDLE.getString("ue-error-required");
                            String ERROR_INVALID = BUNDLE.getString("ue-error-invalid");
                            String PANE_HEADER = BUNDLE.getString("ue-pane-header");
                            String PANE_SUBHEADER = BUNDLE.getString("ue-pane-subheader");
                            String TXT_USEREMAIL_PROMPT_TEXT = BUNDLE.getString("ue-txt-useremail-prompt-text");
                            String BTN_NEXT = BUNDLE.getString("ue-btn-next");
                        }
                    }

                    interface Password {
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

                        interface Flow {
                            String VALIDATE = "VALIDATE_USER_PASSWORD";
                        }

                        interface Strings {
                            String BUNDLE_BASE_NAME = "strings.login.steps";
                            ResourceBundle BUNDLE = ResourceBundle.getBundle(BUNDLE_BASE_NAME);
                            String TOOLTIP_CHANGE_USER = BUNDLE.getString("pw-tooltip-change-user");
                            String BTN_LOGIN = BUNDLE.getString("pw-btn-login");
                            String ERROR_REQUIRED = BUNDLE.getString("pw-error-required");
                            String TXT_PASSWORD_PROMPT_TEXT = BUNDLE.getString("pw-txt-password-prompt-text");
                            String PASSWORD_PANE_HEADER_WELCOME = BUNDLE.getString("pw-pane-header");
                        }
                    }
                }

                interface SideMenu {
                    Class<?> CLASS = LoginSideMenuController.class;
                    Logger LOGGER = Logger.getLogger(CLASS.getName());
                    String ITEM_EXIT = "item-exit";
                    interface Flow {
                        String EXIT = "EXIT_APPLICATION";
                    }

                    interface Strings {
                        String BUNDLE_BASE_NAME = "strings.login.sidemenu";
                        ResourceBundle BUNDLE = ResourceBundle.getBundle(BUNDLE_BASE_NAME);
                        String ITEM_EXIT = "item-exit";
                    }
                }
            }

            interface Main {
                Class<?> CLASS = MainController.class;
                Logger LOGGER = Logger.getLogger(CLASS.getName());

                interface Flow {

                }

                interface Strings {
                    String BUNDLE_BASE_NAME = "strings.main.main";
                    ResourceBundle BUNDLE = ResourceBundle.getBundle(BUNDLE_BASE_NAME);
                    String TITLE = BUNDLE.getString("title");
                }

                interface SideMenu {
                    Class<?> CLASS = MainSideMenuController.class;
                    Logger LOGGER = Logger.getLogger(CLASS.getName());
                    String PANE = "main-sidemenu-pane";
                    /* Seems like we are going to use 'fx:id's */
                    String USER_PANE = "main-sidemenu-user-pane";
                    String USER_ICON = "main-sidemenu-user-icon";
                    String USER_NAME = "main-sidemenu-user-name";
                    String USER_EMAIL = "main-sidemenu-user-email";
                    String ITEM_HOME = "item-home";
                    String ITEM_CASHBOX_MODE = "item-cashbox-mode";
                    String ITEM_SALES = "item-sales";
                    String ITEM_PURCHASING = "item-purchasing";
                    String ITEM_REPORTS = "item-reports";
                    String ITEM_INVENTORY = "item-inventory";
                    String ITEM_CLIENTS = "item-clients";
                    String ITEM_POSTS = "item-posts";
                    String ITEM_COMMENTS = "item-comments";
                    String ITEM_ADMINISTRATION = "item-administration";
                    String ITEM_WEB_SETTINGS = "item-web-settings";
                    String ITEM_SETTINGS = "item-settings";
                    String ITEM_ABOUT = "item-about";
                    String ITEM_LOG_OUT = "item-log-out";
                    interface Flow {
                        String LOG_OUT = "LOG_OUT";
                    }

                    interface Strings {
                        String BUNDLE_BASE_NAME = "strings.main.sidemenu";
                        ResourceBundle BUNDLE = ResourceBundle.getBundle(BUNDLE_BASE_NAME);
                        String ITEM_HOME = "item-home";
                        String ITEM_SALES = "item-sales";
                        String ITEM_PURCHASING = "item-purchasing";
                        String ITEM_INVENTORY = "item-inventory";
                        String ITEM_REPORTS = "item-reports";
                        String ITEM_SETINGS = "item-settings";
                        String ITEM_LOG_OUT = "item-log-out";
                    }
                }
            }
        }
    }

    interface Database {
        String HOST = "localhost";
        int PORT = 3306;
        String NAME = "ce";
        String CHARSET = "utf8mb4";
        String USER = "root";
        String PASSWORD = "12345678";
    }

    interface Meta {
        interface Icons {
            Image ICON16X16 = new Image("/images/logos/logo-16x16.png");
            Image ICON28X28 = new Image("/images/logos/logo-28x28.png");
            Image ICON56X56 = new Image("/images/logos/logo-56x56.png");
            Image ICON64X64 = new Image("/images/logos/logo-64x64.png");
            Image ICON128X128 = new Image("/images/logos/logo-128x128.png");
            Image ICON256X256 = new Image("/images/logos/logo-128x128.png");
        }

        interface Links {
            URI WEBAPP_URI = URI.create("https://example.com");
        }

        interface Colors {
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

        interface Stylesheets {
            String STYLESHEETS_PATH = "/css/";
            String CE_STYLESHEET = STYLESHEETS_PATH + "ce.css";
        }

        interface Fonts {
            String FONTS_PATH = "/fonts/";
            String ROBOTO_BOLD = FONTS_PATH + "Roboto-Bold.ttf";
            String ROBOTO_BLACK = FONTS_PATH + "Roboto-Black.ttf";
            String ROBOTO_BOLD_ITALIC = FONTS_PATH + "Roboto-BoldItalic.ttf";
            String ROBOTO_LIGHT = FONTS_PATH + "Roboto-Light.ttf";
            String ROBOTO_MEDIUM = FONTS_PATH + "Roboto-Medium.ttf";
            String ROBOTO_REGULAR = FONTS_PATH + "Roboto-Regular.ttf";
            InputStream ROBOTO_BOLD_STREAM = CE.App.CLASS.getResourceAsStream(ROBOTO_BOLD);
            InputStream ROBOTO_BLACK_STREAM = CE.App.CLASS.getResourceAsStream(ROBOTO_BLACK);
            InputStream ROBOTO_BOLD_ITALIC_STREAM = CE.App.CLASS.getResourceAsStream(ROBOTO_BOLD_ITALIC);
            InputStream ROBOTO_LIGHT_STREAM = CE.App.CLASS.getResourceAsStream(ROBOTO_LIGHT);
            InputStream ROBOTO_MEDIUM_STREAM = CE.App.CLASS.getResourceAsStream(ROBOTO_MEDIUM);
            InputStream ROBOTO_REGULAR_STREAM = CE.App.CLASS.getResourceAsStream(ROBOTO_REGULAR);
        }
    }
}
