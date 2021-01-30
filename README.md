
# Sample Project in Kotlin


## In this Repo you'll find:
*   A presentation layer that contains an activity (View) and a **ViewModel** per screen (or feature).
*   Reactive UIs using **LiveData** observables.
*   A **data layer** with a repository, one data source which is queried with one-shot operation (no listeners or data streams).
*   Retrofit - A type-safe HTTP client for Android and Java. (https://github.com/square/retrofit) .
*   Picasso - A powerful image downloading and caching library for Android  (https://square.github.io/picasso/).

## What the app does?

This app makes a call to a static json file with a page of products.  It displays the products based on width and height settings in each product.  There is a CanvasUnit field which determines how many segments or spans across the width of the screen there will be. A segment or span will then have a set value.

Example : canvasUnit is 16 and a products width is 8 then the product is alotted half the screen space in the width direction.  Height with a value of 4 would then be the size of 1/4 the screen width.

<img src="https://github.com/AKnght/android-kotlin-managers-special/blob/main/screengif.gif" alt="A demo illustrating the of the app" width="288" height="512" style="display: inline; float: right"/>




## Opening in Android Studio

To open the app in Android Studio, begin by checking out the main branch, and then open the root directory in Android Studio.

Clone the repository:

```
git clone https://github.com/AKnght/android-kotlin-managers-special.git
```
This step checks out the master branch.



## License

MIT License

Copyright (c) 2021 Randy Thedford

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.