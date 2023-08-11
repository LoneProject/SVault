package org.lone64.vault.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.lone64.vault.data.util.PlayerUtil;

public class SkriptVault extends SimpleExpression<Double> {

    private Expression<OfflinePlayer> player;

    public SkriptVault() { }

    public Class<? extends Double> getReturnType() {
        return Double.class;
    }

    public boolean isSingle() {
        return true;
    }

    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        this.player = (Expression<OfflinePlayer>) args[0];
        return true;
    }

    public String toString(Event event, boolean b) {
        return this.player.toString(event, b);
    }

    protected Double[] get(Event e) {
        OfflinePlayer p = this.player.getSingle(e);
        return new Double[]{PlayerUtil.get(p).getValue()};
    }

    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        OfflinePlayer p = this.player.getSingle(e);
        Double num = (Double) delta[0];
        if (mode == Changer.ChangeMode.ADD) {
            PlayerUtil.get(p).setValue(PlayerUtil.get(p).getValue() + num.doubleValue());
        } else if (mode == Changer.ChangeMode.REMOVE) {
            PlayerUtil.get(p).setValue(PlayerUtil.get(p).getValue() - num.doubleValue());
        } else if (mode == Changer.ChangeMode.SET) {
            PlayerUtil.get(p).setValue(num.doubleValue());
        }

    }

    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return mode != Changer.ChangeMode.ADD && mode != Changer.ChangeMode.REMOVE && mode != Changer.ChangeMode.SET ? null : (Class[]) CollectionUtils.array(new Class[]{Double.class});
    }

    static {
        Skript.registerExpression(SkriptVault.class, Double.class, ExpressionType.SIMPLE, "[vault] money of %offlineplayer%", "[vault] %offlineplayer%'s money");
    }

}