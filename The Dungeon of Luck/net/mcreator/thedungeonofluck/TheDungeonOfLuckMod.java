/*    */ package net.mcreator.thedungeonofluck;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.concurrent.ConcurrentLinkedQueue;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.util.Tuple;
/*    */ import net.neoforged.bus.api.IEventBus;
/*    */ import net.neoforged.bus.api.SubscribeEvent;
/*    */ import net.neoforged.fml.common.Mod;
/*    */ import net.neoforged.fml.util.thread.SidedThreadGroups;
/*    */ import net.neoforged.neoforge.common.NeoForge;
/*    */ import net.neoforged.neoforge.event.TickEvent;
/*    */ import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
/*    */ import net.neoforged.neoforge.network.handling.IPlayPayloadHandler;
/*    */ import net.neoforged.neoforge.network.registration.IPayloadRegistrar;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mod("the_dungeon_of_luck")
/*    */ public class TheDungeonOfLuckMod
/*    */ {
/* 30 */   public static final Logger LOGGER = LogManager.getLogger(TheDungeonOfLuckMod.class);
/*    */   
/*    */   public static final String MODID = "the_dungeon_of_luck";
/*    */ 
/*    */   
/*    */   public TheDungeonOfLuckMod(IEventBus modEventBus) {
/* 36 */     NeoForge.EVENT_BUS.register(this);
/* 37 */     modEventBus.addListener(this::registerNetworking);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static boolean networkingRegistered = false;
/*    */ 
/*    */   
/* 46 */   private static final Map<ResourceLocation, NetworkMessage<?>> MESSAGES = new HashMap<>();
/*    */   private static final class NetworkMessage<T extends CustomPacketPayload> extends Record { private final FriendlyByteBuf.Reader<T> reader; private final IPlayPayloadHandler<T> handler;
/* 48 */     private NetworkMessage(FriendlyByteBuf.Reader<T> reader, IPlayPayloadHandler<T> handler) { this.reader = reader; this.handler = handler; } public final String toString() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> toString : (Lnet/mcreator/thedungeonofluck/TheDungeonOfLuckMod$NetworkMessage;)Ljava/lang/String;
/*    */       //   6: areturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #48	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lnet/mcreator/thedungeonofluck/TheDungeonOfLuckMod$NetworkMessage;
/*    */       // Local variable type table:
/*    */       //   start	length	slot	name	signature
/* 48 */       //   0	7	0	this	Lnet/mcreator/thedungeonofluck/TheDungeonOfLuckMod$NetworkMessage<TT;>; } public FriendlyByteBuf.Reader<T> reader() { return this.reader; } public final int hashCode() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> hashCode : (Lnet/mcreator/thedungeonofluck/TheDungeonOfLuckMod$NetworkMessage;)I
/*    */       //   6: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #48	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lnet/mcreator/thedungeonofluck/TheDungeonOfLuckMod$NetworkMessage;
/*    */       // Local variable type table:
/*    */       //   start	length	slot	name	signature
/*    */       //   0	7	0	this	Lnet/mcreator/thedungeonofluck/TheDungeonOfLuckMod$NetworkMessage<TT;>; } public final boolean equals(Object o) { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: <illegal opcode> equals : (Lnet/mcreator/thedungeonofluck/TheDungeonOfLuckMod$NetworkMessage;Ljava/lang/Object;)Z
/*    */       //   7: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #48	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	8	0	this	Lnet/mcreator/thedungeonofluck/TheDungeonOfLuckMod$NetworkMessage;
/*    */       //   0	8	1	o	Ljava/lang/Object;
/*    */       // Local variable type table:
/*    */       //   start	length	slot	name	signature
/* 48 */       //   0	8	0	this	Lnet/mcreator/thedungeonofluck/TheDungeonOfLuckMod$NetworkMessage<TT;>; } public IPlayPayloadHandler<T> handler() { return this.handler; }
/*    */      }
/*    */   
/*    */   public static <T extends CustomPacketPayload> void addNetworkMessage(ResourceLocation id, FriendlyByteBuf.Reader<T> reader, IPlayPayloadHandler<T> handler) {
/* 52 */     if (networkingRegistered)
/* 53 */       throw new IllegalStateException("Cannot register new network messages after networking has been registered"); 
/* 54 */     MESSAGES.put(id, new NetworkMessage(reader, handler));
/*    */   }
/*    */ 
/*    */   
/*    */   private void registerNetworking(RegisterPayloadHandlerEvent event) {
/* 59 */     IPayloadRegistrar registrar = event.registrar("the_dungeon_of_luck");
/* 60 */     MESSAGES.forEach((id, networkMessage) -> registrar.play(id, networkMessage.reader(), networkMessage.handler()));
/* 61 */     networkingRegistered = true;
/*    */   }
/*    */   
/* 64 */   private static final Collection<Tuple<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();
/*    */   
/*    */   public static void queueServerWork(int tick, Runnable action) {
/* 67 */     if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER)
/* 68 */       workQueue.add(new Tuple(action, Integer.valueOf(tick))); 
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void tick(TickEvent.ServerTickEvent event) {
/* 73 */     if (event.phase == TickEvent.Phase.END) {
/* 74 */       List<Tuple<Runnable, Integer>> actions = new ArrayList<>();
/* 75 */       workQueue.forEach(work -> {
/*    */             work.setB(Integer.valueOf(((Integer)work.getB()).intValue() - 1));
/*    */             if (((Integer)work.getB()).intValue() == 0)
/*    */               actions.add(work); 
/*    */           });
/* 80 */       actions.forEach(e -> ((Runnable)e.getA()).run());
/* 81 */       workQueue.removeAll(actions);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/stormwindsky/Téléchargements/the_dungeon_of_luck-1.0.0-neoforge-1.20.4.jar!/net/mcreator/thedungeonofluck/TheDungeonOfLuckMod.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */