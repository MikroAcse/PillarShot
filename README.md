# PillarShot 1.1.0
Config:
```yaml
debug: false
# you can enable or disable whole plugin by changing this and running /ps reload
enabled: true

# Minimal height to be sure that this is a pillar.
checkMinimalHeight: 7
# Minimal Y to check pillars.
checkMinimalY: 45
# Error block count (for example, if checkMinimalHeight is 6: 5 blocks will be checked and 1 of them not).
error: 1
# Error check radius. Set 0 to disable.
errorRadius: 4

# Check for block type.
checkBlockType: false
# Adds JUMP potion effect (disables fall damage, but doesn't give extra jump boost) for a small amount of time.
decreaseFallDamage: true

# Disables check in these worlds.
disabledWorlds:
- disabled_world

# Remove last blocks, placed by player.
removeLastBlocks: 7
# Minimal Y to remove the blocks. Must be bigger than checkMinimalY or 0 to disable.
minimalY: 0

# Disables check in WorldGuard regions.
disableInRegions: true
# Plugin WON'T check players with OTHER gamemodes.
gamemodesEnabled:
- survival

# Permission to bypass check.
bypassPermission: pillarshot.bypass

# Set 0 to disable.
warnCount: 3
# Runs this command when warnCount is reached. Set warnCount to 0 to disable.
# You can run commands (/command), use placeholders %player% and %playername%,
# show messages (just put them as is) and run multiple commands (and show messages) by splitting them with >;
tooMuchWarnsCommand: "/kick %player% %playername%, building pillars is forbidden by the rules!"

# Player has to wait this time (in seconds) to place blocks again
# 0 to disable (not recommended)
placementCooldown: 3

# This command(s) will run every time player builds pillar. Write "" to disable.
# You can run commands (/command), use placeholders %player% and %playername%,
# show messages (just put them as is) and run multiple commands (and show messages) by splitting them with >;
warnedCommand: "/eco take %player% 500>;%playername%, you are fined!"

# Supports UTF-8 text.
# Write "" to disable.
locale:
  noPermission: '&d&l[PillarShot]&r &cYou don''t have enough permissions for this!'
  pluginReloaded: '&d&l[PillarShot]&r &fPlugin has been successfully reloaded!'
  warn: '&d&l[PillarShot]&r &fHey! Don''t build pillars! Use //expand instead.'
  cooldownBlock: '&d&l[PillarShot]&r &fYou will be able to place blocks in %cooldown% seconds.'
  databaseReset: '&d&l[PillarShot]&r &fDatabase has been reset!'
  help: '&d&l[PillarShot]&r &fAvailable commands: &b/ps reload&f, &b/ps reset&f, &b/ps version'
  version: "&d&l[PillarShot]&r &fVersion of the plugin: &b&l"
```
