package com.example.demo.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.wicketstuff.annotation.mount.MountPath;

import java.awt.*;

@MountPath("UserDeleteError")
public class UserDeleteErrorPage extends WebPage {

    public UserDeleteErrorPage(){
       // Label userNameLabel = new Label("userName", userNameModel);
        //add(userNameLabel);

        Link<Void> toUserDeletePageLink = new BookmarkablePageLink<Void>("toUserDeletePage", UserDeletePage.class);
        add(toUserDeletePageLink);
    }
}
