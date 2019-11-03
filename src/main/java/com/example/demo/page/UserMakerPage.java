package com.example.demo.page;

import com.example.demo.page.panel.TopPanelPage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.validation.validator.StringValidator;
import org.wicketstuff.annotation.mount.MountPath;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.example.demo.service.IUserService;

@MountPath("UserMaker")
public class UserMakerPage extends WebPage {

    @SpringBean
    private IUserService userService;

    public UserMakerPage() {

        add(new TopPanelPage("TopPanelPage"));

        IModel<String> userNameModel = Model.of("");
        IModel<String> userPassModel = Model.of("");


        Link<Void> toHomePageLink = new BookmarkablePageLink<>("toHomePage", HomePage.class);
        add(toHomePageLink);

        Form<Void> userInfoForm = new Form<Void>("userInfo") {
            @Override
            protected void onSubmit() {
                String userName = userNameModel.getObject();
                String userPass = userPassModel.getObject();
                String msg = "送信データ : "
                        + userName
                        + ","
                        + userPass;
                System.out.println(msg);



                //TODOエラー処理ができてへん
               var m = userService.registerUser(userName, userPass);
                   setResponsePage(new UserMakerCompPage(m));

            }
        };

            add(userInfoForm);

            //匿名クラス化するので、右辺の型引数を<>から<String>に省略せずに書く
            TextField<String> userNameField = new TextField<String>("userName", userNameModel) {

                //onInitialize() は全てのコンポーネントに備わっている、初期化時の処理。
                //オーバーライドするときはsuper.onInitialize()を忘れずに残しておく。
                @Override
                protected void onInitialize() {
                    super.onInitialize();
                    //文字列の長さを8~32文字にｎ制限するバリデータ
                    StringValidator validator = StringValidator.lengthBetween(8, 32);
                    add(validator);
                }
            };
            userInfoForm.add(userNameField);


        PasswordTextField userPassField = new PasswordTextField("userPass",userPassModel){
          @Override
          protected void onInitialize(){
              super.onInitialize();
              StringValidator validator = StringValidator.lengthBetween(8,32);
              add(validator);
          }
        };
        userInfoForm.add(userPassField);
    }
}
