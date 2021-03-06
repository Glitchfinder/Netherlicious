/*
 * Copyright (c) 2012 Sean Porter <glitchkey@gmail.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.netherlicious;

//* IMPORTS: JDK/JRE
	import java.lang.reflect.Field;
//* IMPORTS: BUKKIT
	import org.bukkit.craftbukkit.CraftWorld;
	import org.bukkit.craftbukkit.generator.NormalChunkGenerator;
	import org.bukkit.event.EventHandler;
	import org.bukkit.event.EventPriority;
	import org.bukkit.event.Listener;
	import org.bukkit.event.world.ChunkLoadEvent;
	import org.bukkit.plugin.PluginManager;
	import org.bukkit.World;
	import org.bukkit.World.Environment;
//* IMPORTS: SPOUT
	//* NOT NEEDED
//* IMPORTS: OTHER
	import net.minecraft.server.ChunkProviderHell;
	import net.minecraft.server.ChunkProviderServer;
	import net.minecraft.server.IChunkProvider;

public class NetherliciousListener implements Listener
{
	private Netherlicious plugin;

	public NetherliciousListener(Netherlicious plugin)
	{
		this.plugin = plugin;
	}

	public void register()
	{
		PluginManager manager;

		manager = plugin.getServer().getPluginManager();
		manager.registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onChunkLoad(ChunkLoadEvent event)
	{
		World world = event.getWorld();

		if(world.getEnvironment() != World.Environment.valueOf("NETHER"))
			return;

		net.minecraft.server.World defaultWorld;
		defaultWorld = ((CraftWorld) world).getHandle();

		IChunkProvider provider = defaultWorld.H();

		if(!(provider instanceof ChunkProviderServer))
			return;

		ChunkProviderServer providerServer = (ChunkProviderServer) provider;

		if(!(providerServer.chunkProvider instanceof NormalChunkGenerator))
			return;

		NormalChunkGenerator providerNormal;
		providerNormal = (NormalChunkGenerator) providerServer.chunkProvider;
		IChunkProvider providerPrivate;

		try
		{
			Field f;
			f = providerNormal.getClass().getSuperclass().getDeclaredField("provider");
			f.setAccessible(true);
			providerPrivate = (IChunkProvider) f.get(providerNormal);
		}
		catch(Exception e)
		{
			return;
		}

		if(!(providerPrivate instanceof ChunkProviderHell))
			return;

		int x = event.getChunk().getX();
		int z = event.getChunk().getZ();
		byte[] buffer = new byte[32768];

		ChunkProviderHell providerHell = (ChunkProviderHell) providerPrivate;
		providerHell.c.a(providerHell, defaultWorld, x, z, buffer);
	}
}
