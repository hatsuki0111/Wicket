package com.example.demo.page;

import org.apache.catalina.User;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("UserMakerComp")
public class UserMakerCompPage extends WebPage {

    public UserMakerCompPage(String m){
       // Label userNameLabel = new Label("userName",userNameModel);
        //add(userNameLabel);

        Label mLabel = new Label("m",m);
        add(mLabel);

        Link<Void> toHomePageLink = new BookmarkablePageLink<Void>("toHomePage",HomePage.class);
        add(toHomePageLink);
    }
}
