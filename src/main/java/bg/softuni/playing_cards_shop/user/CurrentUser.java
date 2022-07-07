//package bg.softuni.playing_cards_shop.user;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.annotation.SessionScope;
//
//@Component
//@SessionScope
//public class CurrentUser {
//
//    private Long id;
//    private String username;
//    private boolean loggedIn;
//    private String email;
//
//    public Long getId() {
//        return id;
//    }
//
//    public CurrentUser setId(Long id) {
//        this.id = id;
//        return this;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public CurrentUser setUsername(String username) {
//        this.username = username;
//        return this;
//    }
//
//    public boolean isLoggedIn() {
//        return loggedIn;
//    }
//
//    public CurrentUser setLoggedIn(boolean loggedIn) {
//        this.loggedIn = loggedIn;
//        return this;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public CurrentUser setEmail(String email) {
//        this.email = email;
//        return this;
//    }
//
//    public void login(CurrentUser currentUser){
//        this.username=currentUser.getUsername();
//        this.email=currentUser.getEmail();
//        this.loggedIn=true;
//    }
//
//    public void logout(){
//        email=null;
//        loggedIn=false;
//        username=null;
//    }
//}
