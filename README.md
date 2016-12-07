# What is Laddr?
Laddr is a capstone project developed by Greg Wood, Alan Simon, Muska Ahmadzai, and Peter Phan - 4 Sheridan Software Development & Network Engineering students.

Here's a short description
>Laddr provides users with relevant volunteering opportunities whether they are using it to complete their necessary hours of community service, or to gain experience in a field that they are pursuing a career in.

>Laddr will be accessible through Web, Android and on iOS as well.

>Not only is this a system where volunteers can easily access volunteering opportunities – but it will also allow organizations who seek volunteers to easily recruit talent as well.

# So what is /laddr_droid?
Here is our Android repo, the source code for our native Android application. If you're looking for our Node.JS backend/full-stack application, head over to [here](https://github.com/weirdvector/Laddr).


# TODO List
- [x] create an Android application.
- [x] publish to the Play Store/Amazon App Marketplace.
- [x] create an iOS application.
- [x] start talks with organizations to pick up / work with Laddr.
- [ ] get Angular to play nice with mobile.
- [x] get picture upload working as intended.


# Environments & Dependencies
laddr_droid is built with Android Studio 2.2.2.

The Android application uses these dependencies:
- Material Design Libraries (AppCompat, Design, CardView)
- ButterKnife for view binding and injections
- Android Annotations for dependency/view plumbing
- Circular Image View for Circular Images...
- Bottom Bar  for a bottom bar navigation implementation
- Picasso for image loading and image requests
- FireBase Core, Messaging for Notifications and FCM Server.
- Google Play Services


# Major Structure Explained

Component / Location | *(Class)* Name | Functionality
--- | --- | ---
*Gradle* | `build.gradle(project)` | Core file for project build, contains the google services version and any project wide dependencies.
*Gradle* | `build.gradle(app)` | Core file for app build, and contains all library dependencies that the app will need, and more specific reference that shouldn't be used in the project gradle.
*com.package* | `android` | Root package of all the src files. Divided into 3 main components, Data for POJOs, Modules for actual functional components, and Utility for all data requests.
*com.android.package* | `data` | Here all the POJO classes are located, Handling Forum Topics, Comments to Postings and the GlobalState.
*com.android.package* | `modules` | Here are all the functional Android classes. These compose of Fragments, Activities, TouchListeners, Adapters and Services varying on function.
*com.android.package* | `utility` | Here is all the data classes, used to either login, apply, query and sign up with our api.
*com.android.data* | `GlobalState` | GlobalState in the project is being treated as Global Object, that will store session values and help validate the user and other functions from login to logout (Potentially not best practice?).
*com.android.modules* | `firebase` | The 2 service classes in this module fulfill two functions. FirebaseInstanceId will obtain and generate a unique id for the device when the app is created. FirebaseMessaging will constantly check for an incoming message and handling it a specific way.  
*com.android.modules* | `forums` | This module requires 2 adapters as it generates 2 recycler views. The first for all the topics in the forum, and the second for all the comments in a topic. A touch listener is included so on click, you can see all the comments of the topic.
*com.android.modules* | `home` | This module holds the home activity, which holds all fragments inside of it. The ApplicationsAdapter is used to display a recycler view of all applied applications and color code their statuses respectively.
*com.android.modules* | `login` | This module handles all user interaction with logging in or signing up. Login Activity contains a holder than can switch between the LoginFragment or the SignUp/UserDetails Fragments. Both of these types of fragments make request calls to our api to validate the user prior to logging them in.
*com.android.modules* | `postings` | This module handles all postings interactions. Using a recycler view, this module requires an adapter for the postings and a touch listener to determine the selection of which ever posting. The map view is handled inside of the PostingContentFragment along with the apply button as well. The apply uses a Utility Task to send the post Request through.
*com.android.modules* | `profile` | This module is used to display information about the user.
*com.android.utility* | `Utility` | This class holds all the data function calls and the Post / Get Requests for our API. Executing any data call will reference Utility and grab the appropriate request it requires.
*res* | `layouts` | All the xml layouts of the fragments, content layouts, and activities used in the application.
