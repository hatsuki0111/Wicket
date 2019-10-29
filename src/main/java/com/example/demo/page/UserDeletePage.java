package com.example.demo.page;

import com.example.demo.service.IUserService;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import java.text.Normalizer;

@MountPath("UserDelete")
public class UserDeletePage extends WebPage {

    @SpringBean
    private IUserService userService;

    public UserDeletePage(){
        IModel<String> userNameModel = Model.of("");

        Link<Void> toHomePageLink = new BookmarkablePageLink<Void>("toHomePage", HomePage.class);
        add(toHomePageLink);

        Form<Void> userInfoForm = new Form<Void>("userInfo"){
            @Override
            protected void onSubmit(){
                String userName = userNameModel.getObject();

                String msg = "送信データ :" + userName;
                System.out.println(msg);

                if( userService.removeUser(userName)){
                    setResponsePage(new UserDeleteCompPage(userNameModel));
                }else{
                    //Todo　Deleteエラーページを作って返すようにする
                    //error("mistake");
                    setResponsePage(new UserDeleteErrorPage());
                }

            }
        };
        add(userInfoForm);

        TextField<String> userNameField = new TextField<>("userName", userNameModel);
        userInfoForm.add(userNameField);

    }
}
