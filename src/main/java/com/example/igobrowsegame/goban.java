package com.example.igobrowsegame;

public class goban {
    private boolean turn = true;
    private int[][] goban = new int[13][13];
    private int koux = 100;
    private int kouy = 100;
    private float kuroP = 0.0f;
    private float shiroP = 6.5f;
    private int passcounter=0;

    public void init() {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                goban[i][j] = 0;
            }
        }
    }

    public void put(String s, int x, int y) {
        int c = 0;
        int opponent = 0;
        if (s.equals("kuro")) {
            c = 1;
            opponent = 2;
        } else {
            c = 2;
            opponent = 1;
        }
        if (iSplaceable(c, x, y)) {
            passcounter=0;
            koux = 100;
            kouy = 100;
            goban[x][y] = c;
            this.changeturn();
            for (int i = -1; i < 2; i+=2) {
                if (isSingleblock(opponent, x + i, y)) {
                    this.getallthree(s);
                } else {
                    this.changeallthree(opponent);
                }
                if (isSingleblock(opponent, x, y + i)) {
                    this.getallthree(s);
                } else {
                    this.changeallthree(opponent);
                }

            }
        }
    }
    public float getpoint(String s){
        switch(s){
            case "kuro":
                return this.kuroP;
            case "shiro":
                return this.shiroP;
            default:
                return 0;
        }
    }

    private boolean exist(int x, int y) {
        return (goban[x][y] != 0);
    }

    public int[][] get() {
        return goban;
    }

    public boolean currentturn() {
        return this.turn;
    }

    private void changeturn() {
        this.turn = this.turn != true;
    }

    public boolean pass(){
        switch(this.passcounter){
            case 0:
                this.passcounter =1;
                break;
            case 1:
                return true;
        }
        this.changeturn();
        return false;
    }

    private boolean iSplaceable(int c, int x, int y) {
        int opponent = 0;
        if (c==1){
            opponent = 2;
        } else {
            opponent = 1;
        }
        if (this.exist(x, y)) {
            return false;
        }
        if (koux == x && kouy == y) {
            return false;
        }
        goban[x][y] = c;
        if (isSingleblock(c, x, y)) {
            goban[x][y]=0;
            this.changeallthree(c);
            goban[x][y]=c;
            for (int i = -1; i < 2; i+=2) {
                if (isSingleblock(opponent, x + i, y)) {
                    this.changeallthree(opponent);
                    goban[x][y]=0;
                    return true;
                } else {
                    this.changeallthree(opponent);
                }
                if (isSingleblock(opponent, x, y + i)) {
                    this.changeallthree(opponent);
                    goban[x][y]=0;
                    return true;
                } else {
                    this.changeallthree(opponent);
                }

            }
            goban[x][y]=0;
            return false;
        }else{
            goban[x][y] = 0;
            this.changeallthree(c);
            return true;
        }
    }

    private boolean isSingleblock(int c, int x, int y) {
        if (x >= 0 && y >= 0 && x <= 12 && y <= 12) {
            if (goban[x][y] == c) {
                goban[x][y] = 3;
                for (int i = -1; i < 2; i += 2) {
                    if (x + i >= 0 && x + i <= 12) {
                            if (goban[x+i][y]==c) {
                                 if(!isSingleblock(c,x+i,y)){
                                     return false;
                                 }
                            } else if (goban[x + i][y] == 0) {
                                return false;
                        }
                    }
                    if (y + i >= 0 && y + i <= 12) {
                        if (goban[x][y+i] == c) {
                                if(!isSingleblock(c,x,y+i)){
                                    return false;
                                }
                        } else if (goban[x][y + i] == 0) {
                            return false;
                    }
                    }
                }

            } else {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    private void getallthree(String s) {
        int count =0;
        int x=100;
        int y=100;
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                if (goban[i][j] == 3) {
                    goban[i][j] = 0;
                    count ++;
                    if(count==1){
                        x=i;
                        y=j;
                    }
                    if (s.equals("kuro")) {
                        this.kuroP += 1;
                    } else {
                        this.shiroP += 1;
                    }
                }
            }
        }
        if(count == 1){
            koux=x;
            kouy=y;
        }
    }
    private void changeallthree(int c){
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                if (goban[i][j] == 3) {
                    goban[i][j] = c;
                }
            }
        }
    }
}

