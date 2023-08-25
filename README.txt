Project assignment from JetBrains Academy (www.hyperskill.org), Java Developer track.

A playable rock-papers-scissors game with an AI opponant that makes random choices, which can be 
played in "classic" or "custom" versions.

The program has a file that includes several player names and previous ratings. When the program 
begins, the player is asked for their name and their ratings is loaded from the file, if a rating 
exists for the player. If not, the player is added to the rating system with a score of 0. The 
player may query for their current score anytime by typing the command "!rating" into the console.

The player is then given an empty line where they can define custom signs. The signs are written
as text seperated by comma. An odd number of signs of any length can be supplied. So for a custom
game with "fire, earth, water, wood, wind", e.g., "earth" and "water" from the AI opponent result 
in the player losing while "wood" and "wind" from the AI means the player wins. The custom list 
is circular and loops back to the beginning in determining winners and losers. Thus, if the player 
chooses "wood", then "wind" and "fire" from the AI will cause the player to lose, while "earth" 
and "water" from the AI gives the player the win. Eqaul signs from the player and the AI result 
in a draw. 

If the player has not defined custom signs and simply presses enter, the classic game is loaded
with three signs: rock, paper, scissors, with the usual rules of winning and losing.

A win from the player adds 100 points to the player score, a draw adds 50 points. No point is 
awarded for losing. If invalid signs are entered during the game, the player is informed and asked 
to input their sign again.

The player and the AI play the games indefinitely until "!exit" is entered by the player. Upon
exit, the player's new score is saved in the ratings file.

August 25th, 2023--description by E. Hsu (nahandove@gmail.com)