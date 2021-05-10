------|LanParty app|------

The application is designed to facilitate the organization of LAN parties.
Anyone can sign up for the program if they are a member, of course.
Members can see which team they are on, who the other members are, other teams,
when the party will be held and where.

Created using: Java, Heroku and PostgreSQL

Application includes:

FUNCTIONS:
increaseTeamsParticipatingNumber
-Increases the value of column teamsparticipating in table statistics

decreaseTeamsParticipatingNumber
-Decreases the value of column teamsparticipating in table statistics

increasePartiesNumber
-Increases the value of column parties in table statistics

decreasePartiesNumber
-Decreases the value of column parties in table statistics

TRIGGERS:
increasePartiesTrigger
-Called on insert on table parties

decreasePartiesTrigger
-Called on delete on table parties

increaseTeamsParticipatingTrigger
-Called on insert on table party_team

decreaseTeamsParticipatingTrigger
-Called on delete on table party_team


Created by Vid Berločnik and Mark Legner
