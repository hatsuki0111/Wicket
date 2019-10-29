package com.example.demo.page.signed;


import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("SignError")
public class SignErrorPage extends WebPage {

    public SignErrorPage(){

        Link<Void> toSignPageLink = new BookmarkablePageLink<Void>("toSignPage", SignPage.class);
        add(toSignPageLink);
    }
}
