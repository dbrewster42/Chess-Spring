package com.chess.gameflow;// import java.util.Arrays;


import com.chess.pieces.*;
import com.chess.board.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Status {
//    private static boolean active;
//    private static boolean check;
//    private static boolean checkMate;
    private boolean active;
    private boolean check;
    private boolean checkMate;

    public Status(){
        active = true;
        check = false;
        checkMate = false;
    }
//    public static void setStatus(){
//        active = true;
//        check = false;
//        checkMate = false;
//    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isCheckMate() {
        return checkMate;
    }

    public void setCheckMate(boolean checkMate) {
        this.checkMate = checkMate;
    }

}