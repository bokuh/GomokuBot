package junghyun.unit;

import junghyun.ai.Game;

public class ChatGame {

    private long longId;

    private Game game;

    private long updateTime = System.currentTimeMillis();

    public ChatGame(long longId, Game game) {
        this.longId = longId;
        this.game = game;
    }

    public long getLongId() {
        return longId;
    }

    public Game getGame() {
        return game;
    }

    public ChatGame onUpdate() {
        this.updateTime = System.currentTimeMillis();
        return this;
    }

    public long getUpdateTime() {
        return this.updateTime;
    }
}
