# Music-streaming-and-Event-Booking-app 

by Team members : Arka Sarkar, Dhruv Yadav, Nikhil Dahiya, Amit Maurya, Harman from **Indraprastha Institute of Information Technolofgy, New Delhi**

## Application 

The aim of this project is the development of **mobile music streaming and event ticket booking application**. The application has an **online music store** where our users can choose what songs to listen to and also create **online** playlists. Our audience can buy a **premium subscription** for an **ad-free experience**. The application also enables the user to **search and book concert tickets**.<br>
**Musical professionals would upload their songs** to the database for our audience to listen to. <br>
**Advertisers can post Ads** for their products. **Event Organisers** can post **events and live concerts**, and **tickets for live concerts** would be sold by **ticketing services** on our application. <br>
All the transactions would be handled by the **banking services** affiliated with our company. 

## Stakeholders

* **Musicians** - Willing to upload their songs to a wider audience
  * Singers
  * Bands
  * Indie artists
  * DJs
  * Instrumentalists
* **Audience/ General Public** - Willing to listen to songs in their leisure time
* **Music Event Handlers/ Organizers** - Willing to publicize their event and sell tickets for the same
* **Event Ticket Buyers** - Willing to buy tickets for the said music events
* **Bankers** - Willing to gain profit by the utilization of their services in our app for selling tickets
* **Advertisers** - Willing to advertise their product on our app

## Stakeholder Queries 
* Musicians - Bands, DJs, Singers, producers, ...
  * Check their current popularity.
  * Find the number of views on their latest song.
  * Check out which are their most popular songs.
  * Compare their statistics with artists of a similar genre.
  * Release their new song.
  * Remove their old album from 2003.

* Users/Audience - Students, Families, Individuals, ...
  * Find all the concerts happening in New Delhi in the next 3 months.
  * Make a new playlist.
  * Book tickets of a live concert
  * Find the top trending songs
  * Listen to a playlist by their favourite artists.
  * Rate a Live Performance.
  * Search for a specific song.

* Advertiser - Companies, Sponsors,..
  * Start a new ad campaign.
  * Calculate the advertising cost for one day.
  * Get the total money spent on ads this month.
  * Delete an advertisement.
  * Updating the current ad Display.
* Event Organisers
  * Get the price of an artist.
  * Check for the availability of an artist.
  * Find the best English band in the available budget.
  * Fetch the singer with more than 5 hits and price under 5,00,000.
  * Contract with Artist and fee payment.
  * Checking Sponsors for an event.
  * Setting a schedule for different artists.
  * Fetching the payment made to the Artist.

* Bankers
  * Get all the transactions made by a particular user in the past year.
  * Refund a particular transaction
  * Find all the users who have made transactions in a different currency.
  * Fetch the payment made by an advertiser.
  * Transferring amount from users to event organizers.

* Ticketing Services
  * Sell tickets for a new live concert.
  * Get how many tickets are bought by somebody.
  * Get how many tickets of ₹ 5000/- were sold in the last concert by Linkin Park.
  * Fetch tickets that were added by an event organizer last month.
  * Find out which event sold the least tickets last month.


### src File Structire 

          src/
          ├── Assets
          │   └── Icons
          │       ├── Main Icons
          │       │   ├── Glowing
          │       │   │   ├── Events.png
          │       │   │   ├── home.png
          │       │   │   └── profile.png
          │       │   └── Normal
          │       │       ├── Events.png
          │       │       ├── home.png
          │       │       ├── profile.png
          │       │       ├── search.png
          │       │       └── settings.png
          │       ├── Music Icons
          │       │   ├── 80s.png
          │       │   ├── 90s.png
          │       │   ├── acoustic.png
          │       │   ├── classical.png
          │       │   ├── country.png
          │       │   ├── disco.png
          │       │   ├── edm.png
          │       │   ├── hip hop.png
          │       │   ├── indie.png
          │       │   ├── jazz.png
          │       │   ├── metal.png
          │       │   ├── pop.png
          │       │   ├── rock.png
          │       │   └── romance.png
          │       └── Player Icons
          │           ├── like1.png
          │           ├── like2.png
          │           ├── loop1.png
          │           ├── loop2.png
          │           ├── next.png
          │           ├── pause.png
          │           ├── play.png
          │           └── prev.png
          ├── ConcertResources
          │   ├── black.png
          │   ├── concert0.jpg
          │   ├── concert1.jpg
          │   ├── concert2.jpg
          │   ├── concert3.jpg
          │   ├── concert4.jpg
          │   ├── concert5.jpg
          │   ├── concert6.jpg
          │   ├── concert7.jpg
          │   ├── concert8.jpg
          │   ├── FindFest
          │   │   ├── PLlist0.jpg
          │   │   ├── PLlist10.jpg
          │   │   ├── PLlist11.jpg
          │   │   ├── PLlist12.png
          │   │   ├── PLlist13.png
          │   │   ├── PLlist14.jpg
          │   │   ├── PLlist15.jpg
          │   │   ├── PLlist16.jpg
          │   │   ├── PLlist17.jpg
          │   │   ├── PLlist18.jpg
          │   │   ├── PLlist19.jpg
          │   │   ├── PLlist1.jpg
          │   │   ├── PLlist20.jpg
          │   │   ├── PLlist21.jpg
          │   │   ├── PLlist22.jpg
          │   │   ├── PLlist23.jpg
          │   │   ├── PLlist24.jpg
          │   │   ├── PLlist25.jpg
          │   │   ├── PLlist2.jpg
          │   │   ├── PLlist3.jpg
          │   │   ├── PLlist4.jpg
          │   │   ├── PLlist5.jpg
          │   │   ├── PLlist6.jpg
          │   │   ├── PLlist7.jpg
          │   │   ├── PLlist8.jpg
          │   │   └── PLlist9.jpg
          │   ├── imagi
          │   │   ├── imag0.jpg
          │   │   ├── imag10.jpg
          │   │   ├── imag11.jpg
          │   │   ├── imag12.jpg
          │   │   ├── imag13.jpg
          │   │   ├── imag14.jpg
          │   │   ├── imag15.jpg
          │   │   ├── imag16.jpg
          │   │   ├── imag17.jpg
          │   │   ├── imag18.jpg
          │   │   ├── imag19.jpg
          │   │   ├── imag1.jpg
          │   │   ├── imag20.jpg
          │   │   ├── imag21.jpg
          │   │   ├── imag22.jpg
          │   │   ├── imag23.jpg
          │   │   ├── imag24.jpg
          │   │   ├── imag25.jpg
          │   │   ├── imag2.jpg
          │   │   ├── imag3.jpg
          │   │   ├── imag4.jpg
          │   │   ├── imag5.jpg
          │   │   ├── imag6.jpg
          │   │   ├── imag7.jpg
          │   │   ├── imag8.jpg
          │   │   └── imag9.jpg
          │   ├── logo.png
          │   └── SERIO___.TTF
          ├── Core
          │   ├── Advertisement.java
          │   ├── SaveFileCache.java
          │   ├── UserAuth.java
          │   ├── User.java
          │   └── UserPlaylist.java
          ├── Database
          │   ├── DataController.java
          │   ├── DBConnectionAdvertiser.java
          │   ├── DBConnectionBanking.java
          │   ├── DBConnectionEventOrg.java
          │   ├── DBConnection.java
          │   ├── DBConnectionMusicProf.java
          │   └── DBConnectionTicketingServ.java
          ├── Exception
          │   ├── CannotAddPlaylsitException.java
          │   ├── ConnectionInvalidException.java
          │   ├── IncorrectDateException.java
          │   ├── InsufficientBalanceException.java
          │   ├── InvalidEmailException.java
          │   ├── InvalidEntryException.java
          │   ├── InvalidPasswordException.java
          │   ├── InvalidSignupException.java
          │   ├── InvalidTableNameException.java
          │   ├── InvalidUsernamePassowordException.java
          │   ├── MyException.java
          │   ├── SaveStateException.java
          │   └── UserExistsException.java
          ├── Resources
          │   ├── 011ef38f006b802fe77d59e98155e7df_account-avatar-female-person-profile-student-user-icon_512-512.png
          │   ├── 8-bitwonder.ttf
          │   ├── Album Art
          │   │   ├── badguy.png
          │   │   ├── bangarang.png
          │   │   ├── comatose.png
          │   │   ├── everythingiwanted.png
          │   │   ├── godzilla.png
          │   │   └── shakeitoff.png
          │   ├── Albums
          │   │   ├── akbum5.jpg
          │   │   ├── album1.jpg
          │   │   ├── album2.jpg
          │   │   ├── album3.jpg
          │   │   ├── album4.jpg
          │   │   └── album6.jpg
          │   ├── Artist Icons
          │   │   ├── billie.png
          │   │   ├── eminem.png
          │   │   ├── linkinpark.png
          │   │   ├── skillet.png
          │   │   ├── skrillex.png
          │   │   └── tswift.png
          │   ├── Best of artists
          │   │   ├── artist1.PNG
          │   │   ├── artist2.PNG
          │   │   ├── artist3.PNG
          │   │   ├── artist4.PNG
          │   │   ├── artist5.PNG
          │   │   └── artist6.PNG
          │   ├── bg.jpg
          │   ├── Charts
          │   │   ├── chart1.PNG
          │   │   ├── chart2.PNG
          │   │   ├── chart3.PNG
          │   │   ├── chart4.PNG
          │   │   ├── chart5.PNG
          │   │   └── chart6.PNG
          │   ├── circle-cropped.png
          │   ├── diljit.jpg
          │   ├── grey-bg.png
          │   ├── homepage.png
          │   ├── homepage_signup.png
          │   ├── homepage_user.png
          │   ├── Icons
          │   │   ├── love_icon.png
          │   │   └── profile_icon.png
          │   ├── img1.png
          │   ├── img2.png
          │   ├── img3.jpg
          │   ├── logo.png
          │   ├── Main Icons
          │   │   ├── Glowing
          │   │   │   ├── Events.png
          │   │   │   ├── home.png
          │   │   │   └── profile.png
          │   │   └── Normal
          │   │       ├── Events.png
          │   │       ├── home.png
          │   │       ├── profile.png
          │   │       ├── search.png
          │   │       └── settings.png
          │   ├── Music Icons
          │   │   ├── 80s.png
          │   │   ├── 90s.png
          │   │   ├── acoustic.png
          │   │   ├── classical.png
          │   │   ├── country.png
          │   │   ├── disco.png
          │   │   ├── edm.png
          │   │   ├── hindi.png
          │   │   ├── hip hop.png
          │   │   ├── indie.png
          │   │   ├── jazz.png
          │   │   ├── metal.png
          │   │   ├── pop.png
          │   │   ├── random.png
          │   │   ├── rock.png
          │   │   └── romance.png
          │   ├── oblivion-font.ttf
          │   ├── QuarcaNormBold.otf
          │   ├── sample_vid1.mp4
          │   ├── SERIO___.TTF
          │   ├── TaylorSwift.jpg
          │   └── user.png
          └── Ui
              ├── AddPlaylist
              │   ├── AddPlaylistController.java
              │   ├── AddPlaylist.css
              │   └── AddPlaylist.fxml
              ├── BackendUsers
              │   ├── Advertiser
              │   │   ├── AdvertiserController.java
              │   │   ├── Advertiser.css
              │   │   └── Advertiser.fxml
              │   ├── BankingServices
              │   │   ├── BankingServController.java
              │   │   ├── BankingServ.css
              │   │   └── BankingServ.fxml
              │   ├── EventOrganisers
              │   │   ├── EventOrganisersController.java
              │   │   ├── EventOrganisers.css
              │   │   └── EventOrganisers.fxml
              │   ├── MasterController.java
              │   ├── MusicProffesionals
              │   │   ├── MusicProfController.java
              │   │   ├── MusicProf.css
              │   │   └── MusicProf.fxml
              │   ├── SelectUser.css
              │   ├── SelectUser.fxml
              │   └── TicketingServ
              │       ├── TicketingServController.java
              │       ├── TicketingServ.css
              │       └── TicketingServ.fxml
              ├── BookEvents
              │   ├── BookEventController.java
              │   ├── BookEvent.css
              │   └── BookEvent.fxml
              ├── Events
              │   ├── AllEvents.java
              │   ├── Event.css
              │   ├── SelectShow.css
              │   └── SelectShow.java
              ├── MainPage
              │   ├── mainPageStyle.css
              │   ├── MainScreenController.java
              │   └── MainScreen.fxml
              ├── MyBookings
              │   ├── MyBookingsController.java
              │   ├── MyBookings.css
              │   └── MyBookings.fxml
              ├── Player
              │   ├── PlayerController.java
              │   ├── Player Icons
              │   │   ├── like1.png
              │   │   ├── like2.png
              │   │   ├── loop1.png
              │   │   ├── loop2.png
              │   │   ├── next.png
              │   │   ├── pause.png
              │   │   ├── play.png
              │   │   └── prev.png
              │   └── PlayerScreen.fxml
              ├── ProfilePage
              │   ├── EditProfileController.java
              │   ├── editProfilePage.fxml
              │   ├── ProfilePageController.java
              │   ├── ProfilePage.css
              │   └── ProfilePage.fxml
              ├── Search
              │   ├── AllPlaylistsController.java
              │   ├── AllPlaylists.fxml
              │   ├── M.java
              │   ├── OnButtonClickController.java
              │   ├── OnButtonClick.fxml
              │   ├── SearchPageController.java
              │   ├── SearchPage.css
              │   └── SearchPage.fxml
              ├── SearchFilter
              │   ├── SearchFilterController.java
              │   ├── SearchFilter.css
              │   └── SearchFilterPage.fxml
              ├── SignupLoginMain
              │   ├── LoginBox.fxml
              │   ├── LoginBox.java
              │   ├── LoginSignupController.java
              │   ├── Main.css
              │   ├── Main.fxml
              │   ├── Main.java
              │   └── Signup.fxml
              └── YourLibrary
                  ├── ActivityWindow.css
                  ├── YourLibraryController.java
                  ├── YourLIbrary.css
                  └── YourLibrary.fxml

