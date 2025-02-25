package org.cryptodepositbot;

import org.telegram.telegrambots.meta.api.objects.Message;

import static org.cryptodepositbot.MenuList.showAdminPanel;
import static org.cryptodepositbot.MenuList.showMenu;

public class CallbackController {
    public static void navigate(String command, Message message) {
        boolean isAdmin = AdminService.isAdmin(message.getChat().getId());

        System.out.println("Callback: "+command+"\tUser: " + message.getChat().getUserName());
        switch (command) {
            case "show_admin_panel":
                Menu.editInlineKeyboard(message, showAdminPanel());
                break;
            case "show_menu":
                Menu.editInlineKeyboard(message, showMenu(isAdmin));
                break;
            case "show_all_users":
                AdminController.showAllUsers(message.getChat().getId(),String.valueOf(message.getChatId()));
                break;
            default:
        }

    }
}
