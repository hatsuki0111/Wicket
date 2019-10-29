package com.example.demo.page.chat;

import com.example.demo.MySession;
import com.example.demo.data.Chat;
import com.example.demo.service.IChatService;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.apache.wicket.markup.html.basic.Label;
import java.awt.*;
import java.util.List;



@AuthorizeInstantiation(Roles.USER)
@MountPath("ChatListPage")
public class ChatListPage extends WebPage {

    @SpringBean
    private IChatService chatService;


    public ChatListPage() {
       // Label userNameLabel = new Label("userName", userNameModel);
        //add(userNameLabel);


        IModel<List<Chat>> chatModel = Model.ofList(chatService.findChat());

        ListView<Chat> usersLV = new ListView<Chat>("users", chatModel) {
           @Override
            protected void populateItem(ListItem<Chat> listItem){

               IModel<Chat> itemModel = listItem.getModel();
               Chat chat = itemModel.getObject();

               IModel<String> userNameModel = Model.of(chat.getUserName());
               Label userNameLabel = new Label("userName", userNameModel);
               listItem.add(userNameLabel);

               IModel<String> msgBodyModel = Model.of(chat.getMsgBody());
               Label msgBodyLabel = new Label("msgBody", msgBodyModel);
               listItem.add(msgBodyLabel);

           }
        };
        add(usersLV);

        Link<Void> toChatPageLink = new BookmarkablePageLink<>("toChatPage", ChatPage.class);
        add(toChatPageLink);
    }
}
