package com.example.demo.page.chat;

import com.example.demo.service.IChatService;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;
import org.wicketstuff.annotation.mount.MountPath;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.awt.geom.Area;

@AuthorizeInstantiation(Roles.USER)
@MountPath("ChatPage")
public class ChatPage extends WebPage {

    @SpringBean
    private IChatService chatService;

    public ChatPage(){

        IModel<String> userNameModel = Model.of("");
        IModel<String> msgBodyModel = Model.of("");

        Form<Void> userInfoForm = new Form<Void>("userInfo"){
            @Override
            protected void onSubmit(){
                String userName = userNameModel.getObject();
                String msgBody = msgBodyModel.getObject();
                String msg = "送信データ:" + userName +","+msgBody;

                System.out.println(msg);
                chatService.registerChat(userName, msgBody);
                setResponsePage(new ChatListPage());

            }
        };
        add(userInfoForm);

        TextField<String> userNameField = new TextField<>("userName",userNameModel);
        userInfoForm.add(userNameField);


        TextArea<String> msgBodyArea = new TextArea<>("msgBody",msgBodyModel);
        userInfoForm.add(msgBodyArea);

        Link<Void> toChatListPageLink = new BookmarkablePageLink<>("toChatListPage", ChatListPage.class);
        add(toChatListPageLink);
    }
}
