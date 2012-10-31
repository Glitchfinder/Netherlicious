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
	import java.util.logging.Logger;
//* IMPORTS: BUKKIT
	import org.bukkit.plugin.java.JavaPlugin;
//* IMPORTS: SPOUT
	//* NOT NEEDED
//* IMPORTS: OTHER
	//* NOT NEEDED

public class Netherlicious extends JavaPlugin
{
	private	NetherliciousListener listener;
	public Logger log;

	public void onLoad()
	{
		this.log = this.getLogger();
		this.listener = new NetherliciousListener(this);
	}

	public void onEnable()
	{
		this.listener.register();
	}
	
	public void onDisable() {}
}
