package org.cryptodepositbot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class CallbackController {
    public static void navigate(String command, Message message) {
        boolean isAdmin = AdminService.isAdmin(message.getChat().getId().toString());

        System.out.println("Callback: "+command+"\tUser: " + message.getChat().getUserName());
        switch (command) {
            case "show_admin_panel":
                CryptoDepositBot.send(Menu.editInlineKeyboard(message, MenuList.showAdminPanel()));
                break;
            case "show_menu":
                CryptoDepositBot.send(Menu.editInlineKeyboard(message, MenuList.showMenu(isAdmin)));
                break;
            case "show_all_users":
                AdminController.showAllUsers(message.getChat().getId().toString());
                break;
            case "show_deposit_introduction":
                CryptoDepositBot.send(Menu.editInlineKeyboard(message, MenuList.showDepositIntroduction()));
                break;
            case "confirm_rules":
                CryptoDepositBot.send(Menu.editInlineKeyboard(message, MenuList.addDeposit()));
                break;
            case "add_deposit":
                AdminController.sendMessageToAllAdmins(Menu.createInlineKeyboard(MenuList.notifyNewDeposit(message.getChat().getUserName())));
                CryptoDepositBot.send(new SendMessage(message.getChat().getId().toString(), "Депозит будет обрабатываться до 48 часов. Ожидайте..."));
                CryptoDepositBot.send(new DeleteMessage(message.getChatId().toString(),message.getMessageId()));

                CryptoDepositBot.send(Menu.createInlineKeyboard(message.getChatId().toString(), MenuList.showMenu(isAdmin)));
            default:
        }

    }
}
