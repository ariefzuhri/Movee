# Movee

[![GitHub release][release-shield]][release-url]
[![MIT License][license-shield]][license-url]

<a><img src="https://i.imgur.com/HMYw4wp.png" /></a>

**Movee, powered by [TMDb](https://developers.themoviedb.org/), will help you discover your new movies and tv shows.** Movee demonstrates the use of modern design approaches, [Android Jetpack](https://developer.android.com/jetpack), to make app robust. This app was developed as a capstone project in Dicoding's [Belajar Android Jetpack Pro](https://www.dicoding.com/academies/129) (Learn Android Jetpack Pro) class.

## Download
Check out the [release page](https://github.com/ariefzuhri/Movee/releases) and download the latest apk.

## Architecture and Tech-stack
- [MVVM architecture pattern](https://developer.android.com/jetpack/guide#recommended-app-arch)
- [Android Architecture Components](https://developer.android.com/jetpack/androidx/explorer), specifically [Room](https://developer.android.com/topic/libraries/architecture/room), [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [Material Components](https://material.io/develop/android), and [Paging](https://developer.android.com/topic/libraries/architecture/paging)
- [Retrofit](https://github.com/square/retrofit), REST client framework
- [Gson](https://github.com/google/gson), parsing the JSON format
- [OkHttp](https://github.com/square/okhttp), HTTP inspector
- [BubbleTabBar](https://github.com/akshay2211/BubbleTabBar), bottom navigation bar
- [Glide](https://github.com/bumptech/glide), image loading and caching
- [FlexboxLayout](https://github.com/google/flexbox-layout), flexbox layout
- [Facebook Shimmer](https://github.com/facebook/shimmer-android), shimmering effect on loading screen
- [RoundedImageView](https://github.com/vinc3m1/RoundedImageView), image view with rounded corners

## Configuration
1. Clone this repository and import it into Android Studio (`git clone https://github.com/ariefzuhri/Movee.git`).
2. Get your TMBD API key [here](https://developers.themoviedb.org/3/getting-started/introduction).
3. Open `local.properties` in root directory.
4. Assign your api key by adding this new line `tmdb.api.key=YOUR_API_KEY`.

## 🤝 Support
Any contributions, issues, and feature requests are welcome.

Give a ⭐️ if you like this project.

## License
This project is licensed under the MIT License. See the [`LICENSE`](https://github.com/ariefzuhri/Movee/blob/master/LICENSE) file for details.

## Acknowledgments
- [Figma](https://www.figma.com)
- [Iconify](https://www.figma.com/community/plugin/735098390272716381/Iconify)
- [Iconly](https://www.figma.com/community/plugin/861001888228800074/Iconly-v2.3)
- [NestedScrollableHost](https://github.com/android/views-widgets-samples/blob/master/ViewPager2/app/src/main/java/androidx/viewpager2/integration/testapp/NestedScrollableHost.kt)
- [Shields.io](https://shields.io/)
- [TMDb](https://www.themoviedb.org/)

## To-do List
- [ ] Rewrite code from Java to Kotlin

[release-shield]: https://img.shields.io/github/v/release/ariefzuhri/Movee?include_prereleases&style=for-the-badge
[release-url]: https://github.com/ariefzuhri/Movee/releases
[license-shield]: https://img.shields.io/github/license/ariefzuhri/Movee?style=for-the-badge
[license-url]: https://github.com/ariefzuhri/Movee/blob/master/LICENSE
