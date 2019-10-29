package com.example.demo.page.signed;


import com.example.demo.MySession;
import com.example.demo.data.AuthUser;
import com.example.demo.page.HomePage;
import com.example.demo.page.chat.ChatListPage;
import com.example.demo.page.chat.ChatPage;
import com.example.demo.service.IUserService;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.apache.wicket.markup.html.list.ListView;

import java.util.List;

//どの役割のユーザであれば表示してよいか。
//セッションのgetRoleメソッドがUSERであれば表示し、それ以外は表示しない。
@AuthorizeInstantiation(Roles.USER)
@MountPath("Signed")
public class SignedPage extends WebPage {

    @SpringBean
    private IUserService userService;

    public SignedPage(){

        //セッション認証したユーザ名を取得
        add(new Label("userName",MySession.get().getUserName()));

        Link<Void> signoutLink = new Link<Void>("signout"){

            @Override
            public void onClick(){
                //セッションの破棄
                MySession.get().invalidate();
                //SignPageへ移動
                setResponsePage(SignPage.class);
            }
        };
        add(signoutLink);

        // Service からデータベースのユーザ一覧をもらい、Modelにする
        // List型のモデルは Model.ofList(...) で作成する。
        IModel<List<AuthUser>> authUsersModel = Model.ofList(userService.findAuthUsers());

        //List型のモデルを表示する ListView
        ListView<AuthUser> usersLV = new ListView<AuthUser>("users",authUsersModel){
            @Override
            protected void populateItem(ListItem<AuthUser> listItem){
                // List型のモデルから、 <li>...</li> ひとつ分に分けられたモデルを取り出す
                IModel<AuthUser> itemModel = listItem.getModel();
                AuthUser authUser = itemModel.getObject();//もともとのListのn番目の要素

                // インスタンスに入れ込まれたデータベースの検索結果を、列（＝フィールド変数）ごとにとりだして表示する
                // add する先が listItem になることに注意。
                IModel<String> userNameModel = Model.of(authUser.getUserName());
                Label userNameLabel = new Label("userName", userNameModel);
                listItem.add(userNameLabel);

                IModel<String> userPassModel = Model.of(authUser.getUserPass());
                Label userPassLabel = new Label("userPass", userPassModel);
                listItem.add(userPassLabel);
            }
        };
        add(usersLV);


        Link<Void> toChatListPageLink = new BookmarkablePageLink<>("toChatListPage", ChatListPage.class);
        add(toChatListPageLink);

        Link<Void> toChatPageLink = new BookmarkablePageLink<>("toChatPage", ChatPage.class);
        add(toChatPageLink);

      /*  add(new Link<>("toChatListPage") {
            @Override
            public void onClick() {
                setResponsePage(ChatListPage.class);
            }
        });*/
    }
}
