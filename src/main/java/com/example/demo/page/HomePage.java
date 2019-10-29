package com.example.demo.page;

import com.example.demo.page.signed.SignPage;
import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.annotation.mount.MountPath;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import com.example.demo.service.ISampleService;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;

@WicketHomePage
@MountPath("Home")
public class HomePage extends WebPage {

    @SpringBean
    private ISampleService service;


    public HomePage() {
        IModel<String> youModel = Model.of("Wicket-Spring-Boot");
        Label youLabel = new Label("you", youModel);
        add(youLabel);

        IModel<String> gakusekiModel = Model.of("b2170800");
        Label gakusekiLabel = new Label("gakuseki",gakusekiModel);
        add(gakusekiLabel);

        add(new Label("name",Model.of("齊藤初輝")));

        IModel<String> timeModel = Model.of(service.makeCurrentHMS());
        Label timeLabel = new Label("time",timeModel);
        add(timeLabel);

        IModel<Integer> randModel = Model.of(service.makeRandInt());
        Label randLabel = new Label("rand",randModel);
        add(randLabel);

        Link<Void> toUserMakerLink = new BookmarkablePageLink<>("toUserMaker",UserMakerPage.class);
        add(toUserMakerLink);

        Link<Void> toUserDeletePageLink = new BookmarkablePageLink<Void>("toUserDeletePage",UserDeletePage.class);
        add(toUserDeletePageLink);

        Link<Void> toSignPageLink = new BookmarkablePageLink<Void>("toSignPage", SignPage.class);
        add(toSignPageLink);
    }

}