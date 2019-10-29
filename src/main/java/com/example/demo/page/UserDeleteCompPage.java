package com.example.demo.page;


import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.wicketstuff.annotation.mount.MountPath;
import org.apache.wicket.markup.html.basic.Label;

@MountPath("UserDeleteComp")
public class UserDeleteCompPage extends WebPage {

    public UserDeleteCompPage(IModel<String> userNameModel){
        Label userNameLabel = new Label("userName", userNameModel);
        add(userNameLabel);

        Link<Void> toHomePageLink = new BookmarkablePageLink<Void>("toHomePage" ,HomePage.class);
        add(toHomePageLink);
    }
}
