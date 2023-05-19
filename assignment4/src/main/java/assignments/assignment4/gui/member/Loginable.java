package assignments.assignment4.gui.member;

/**
 * Interface yang akan diimplementasikan oleh AbstractMemberGUI
 **/
public interface Loginable {
    boolean login(String id, String password);
    void logout();
    String getPageName();
}