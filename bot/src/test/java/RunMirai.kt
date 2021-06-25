
import me.agape.example.PluginMain
import net.mamoe.mirai.alsoLogin
import net.mamoe.mirai.console.MiraiConsole
import net.mamoe.mirai.console.plugin.PluginManager.INSTANCE.enable
import net.mamoe.mirai.console.plugin.PluginManager.INSTANCE.load
import net.mamoe.mirai.console.terminal.MiraiConsoleTerminalLoader

suspend fun main() {
    MiraiConsoleTerminalLoader.startAsDaemon()

   PluginMain.INSTANCE.load();
    PluginMain.INSTANCE.enable();

    val bot = MiraiConsole.addBot(0, "") {
        fileBasedDeviceInfo()
    }.alsoLogin()

    MiraiConsole.job.join()
}