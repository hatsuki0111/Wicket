package com.example.demo.page.panel;


import com.example.demo.page.HomePage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

public class TopPanelPage extends Panel {
    public TopPanelPage(String id){
        super(id);
        add(new Link<>("toHomePage") {
            @Override
            public void onClick() {
                setResponsePage(HomePage.class);
            }
        });
    }
}
