# FactCheckBot
A Discord bot using Discord4J that works with 
[Google's Fact Check Explorer](https://toolbox.google.com/factcheck/explorer) API to allow users to
conveniently make fact check searches on the spot.

This bot was inspired by discussions regarding the 2020 US presidential debates.

Keep in mind Google's search isn't perfect and that it should not be used as an end-all solution for
fact checking.

# Self-Hosting
This bot was built with self-hosting in mind, which means you can host your own FactCheckBot.
I have a personal instance of it, but I discourage people from inviting it unless the servers it's 
in are small (see requirements below for an explanation). Contact me directly if you can't host the
bot yourself and would like to invite mine. That said, self-hosting comes with a few caveats:
- Requires creating your own Discord bot using their
[Developer Portal](https://discord.com/developers/applications).
- Requires a Google API key for Fact Check Tools using their
[Developer Portal](https://console.developers.google.com/apis/dashboard).
    - API keys have a maximum amount quota in a period, which means you might only be able to use 
    the search a limited amount of times. If you're planning to use this bot on a few small servers,
    it shouldn't be a problem, but distributing it far and wide will probably have you reach the 
    limit pretty quickly and temporarily stop the bot from being able to perform searches - this is
    why I discourage people from inviting my personal bot. I think for Fact Check you're allowed 300 
    searches per minute though, which should give a lot of leeway.
    
You will have to acquire these on your own, but it's not difficult to obtain them - more 
importantly, it's free. On your first time running the bot, you will be prompted to make a new 
config file; this will store information needed for it to log into Discord and use Google's search. 
Replace the values in there with your bot token and API key respectively, log in, and you should be 
good to go. Be sure to log out before closing the bot. Like other bots, this bot will only be online
as long as it's running on a host computer.

# Supported Features
- Basic command line functionality:
    - login
        - Logs the bot in using the Discord bot token and Google API key specified in 
        config.properties.
    - logout
        - Logs the bot out. It's preferred that you do this before closing the bot, otherwise it
        will stay online for a while even after it's no longer responding.
    - Users will be prompted to create a new config.properties file if it is missing.
- Reads Discord messages for supported commands:
    - Default prefix: ^ (shift + 6)
    - fact-check query
        - Calls a Google Fact Check search using the provided query and returns the top 3 results.
        
# Planned Features
- A GUI view.
- Listing the total number of results and how many results were excluded from the top 3.
- The option to filter out foreign results (perhaps by language code).
    