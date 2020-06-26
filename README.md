# Music-streaming-and-Event-Booking-app 

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

