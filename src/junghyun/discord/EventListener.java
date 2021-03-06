package junghyun.discord;

import junghyun.discord.db.Logger;
import junghyun.discord.ui.MessageManager;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.GuildCreateEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.shard.DisconnectedEvent;
import sx.blah.discord.handle.obj.IMessage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EventListener {

    private static boolean isEndLoadGuilds = false;

    public static void onStartLoadGuilds() {
        EventListener.isEndLoadGuilds = false;

        Runnable task = EventListener::onEndLoadGuilds;

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.schedule(task, Settings.LOADING_TIME, TimeUnit.SECONDS);
    }

    private static void onEndLoadGuilds() {
        BotManager.setOfficialChannel(BotManager.getClient().getGuildByID(Settings.OFFICIAL_GUILD_ID).getChannelByID(Settings.RESULT_CHANNEL_ID));
        EventListener.isEndLoadGuilds = true;
    }

    @EventSubscriber
    public void DisconnectedEvent(DisconnectedEvent event) {
        EventListener.onStartLoadGuilds();
    }

    @EventSubscriber
    public void onMessageReceivedEvent(MessageReceivedEvent event) {
        if ((event.getMessage().getType() != IMessage.Type.DEFAULT)
                || (event.getMessage().getContent().length() < 2)
                || (event.getMessage().getContent().toCharArray()[0] != Settings.PREFIX)) return;
        BotManager.processCommand(event);
    }

    @EventSubscriber
    public void onGuildCreateEvent(GuildCreateEvent event) {
        try {
            if (EventListener.isEndLoadGuilds) {
                MessageManager.getInstance(event.getGuild()).sendHelp(event.getGuild().getSystemChannel());
                MessageManager.getInstance(event.getGuild()).sendLanguageInfo(event.getGuild().getSystemChannel());
                Logger.loggerInfo("Join server : " + event.getGuild().getName());
            }
        } catch (Exception e) {
            Logger.loggerInfo("Load server : " + event.getGuild().getName());
        }
    }

}
