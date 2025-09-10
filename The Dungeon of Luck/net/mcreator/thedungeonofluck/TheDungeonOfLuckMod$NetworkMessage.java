/*    */ package net.mcreator.thedungeonofluck;
/*    */ 
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
/*    */ import net.neoforged.neoforge.network.handling.IPlayPayloadHandler;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class NetworkMessage<T extends CustomPacketPayload>
/*    */   extends Record
/*    */ {
/*    */   private final FriendlyByteBuf.Reader<T> reader;
/*    */   private final IPlayPayloadHandler<T> handler;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lnet/mcreator/thedungeonofluck/TheDungeonOfLuckMod$NetworkMessage;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #48	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lnet/mcreator/thedungeonofluck/TheDungeonOfLuckMod$NetworkMessage;
/*    */     // Local variable type table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	7	0	this	Lnet/mcreator/thedungeonofluck/TheDungeonOfLuckMod$NetworkMessage<TT;>;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lnet/mcreator/thedungeonofluck/TheDungeonOfLuckMod$NetworkMessage;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #48	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lnet/mcreator/thedungeonofluck/TheDungeonOfLuckMod$NetworkMessage;
/*    */     // Local variable type table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	7	0	this	Lnet/mcreator/thedungeonofluck/TheDungeonOfLuckMod$NetworkMessage<TT;>;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lnet/mcreator/thedungeonofluck/TheDungeonOfLuckMod$NetworkMessage;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #48	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lnet/mcreator/thedungeonofluck/TheDungeonOfLuckMod$NetworkMessage;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */     // Local variable type table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	8	0	this	Lnet/mcreator/thedungeonofluck/TheDungeonOfLuckMod$NetworkMessage<TT;>;
/*    */   }
/*    */   
/*    */   private NetworkMessage(FriendlyByteBuf.Reader<T> reader, IPlayPayloadHandler<T> handler) {
/* 48 */     this.reader = reader; this.handler = handler; } public FriendlyByteBuf.Reader<T> reader() { return this.reader; } public IPlayPayloadHandler<T> handler() { return this.handler; }
/*    */ 
/*    */ }


/* Location:              /home/stormwindsky/Téléchargements/the_dungeon_of_luck-1.0.0-neoforge-1.20.4.jar!/net/mcreator/thedungeonofluck/TheDungeonOfLuckMod$NetworkMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */