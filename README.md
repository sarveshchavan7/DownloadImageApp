# DownloadImageApp
- Download Image from URL and store it in External Storage (SD card) Using AsyncTask
- It is rotation proff even if the screen orientation is rotated the progressbar will still keep updating it is Achieved by using fragments.
- setRetainInstance(true) allows fragment not to get destroyed but onAttah and onDetach is called in the fragment when the activity associated with it is destroyed and created each time when screen orientaion changes. 
- first onCreate of activity is called and then onAttach of fragmanet is called but once the screen is rotated then onAttach (fragment) is called before onCreate (activity). check activity and fragment lifecycle for more info.
- We get a new instance of the activity inside onAttach using that in AsyncTask class we keep updating the progressbar associated 
with the current activity and not the previous one which was destroyed.
