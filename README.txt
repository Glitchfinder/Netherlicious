﻿== Netherlicious ==

  Source: https://github.com/Glitchfinder/Netherlicious/
  Issue Tracker: https://github.com/Glitchfinder/Netherlicious/issues/

Netherlicios is a simple plugin that fixes a very big issue with the
current version of bukkit. Specifically, it fixes an issue related to
wither spawning. Currently, when a world is unloaded through some
process, such as a server shutting down, all nether fortresses that have
been generated are left with their specialized data completely unsaved.
This means that, once the world is loaded again, any nether fortresses
that have been previously generated are left completely impotent, unable
to spawn random blazes or wither skeletons as originally intended. This
plugin will check for a nether fortress when a chunk is loaded, and will
reset the spawn data so that the specialized mobs are once more allowed
to spawn there.
