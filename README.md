
📱 **App Approval System**

An Android application built using **Kotlin** and **Jetpack Compose** to list installed apps and allow users to approve or reject them. It eliminates the need for a separate admin panel by enabling in-app approval functionality.


🎯 **Features**

✅ List all installed apps on the device  
✅ Approve or reject apps directly within the app  
✅ Save app status using **Room Database** for persistence  
✅ Launch approved apps directly from the app list  
✅ Intuitive UI with smooth user experience using Jetpack Compose  


🛠️ **Tech Stack**

- **Kotlin** – Modern Android development language  
- **Jetpack Compose** – Declarative UI for Android  
- **Room Database** – Local data persistence  
- **Coil** – Image loading and caching  
- **Material Design** – Clean and responsive UI  


### 🚀 **Getting Started**

Follow these steps to run the project locally:

 1. **Clone the Repository**
```bash
git clone https://github.com/your-username/App-Approval-System.git
cd App-Approval-System
```

 2. **Open in Android Studio**
- Open the project in **Android Studio Flamingo+**.
- Sync the project to download dependencies.

 3. **Add Dependencies**
Ensure the following dependencies are added to your `app/build.gradle`:

```groovy
// Coil for Image Loading
implementation "io.coil-kt:coil-compose:2.2.2"

// Room Database
implementation "androidx.room:room-runtime:2.5.0"
kapt "androidx.room:room-compiler:2.5.0"
```

4. **Build and Run**
- Run the project on an emulator or a physical device.  
- Grant necessary permissions to list apps.



⚡ **How It Works**

1. **List Installed Apps:**  
   - The app displays all installed apps with their icons and names.  

2. **Approve or Reject Apps:**  
   - Tap on an app to approve or reject it.  
   - Approved apps can be launched directly.  

3. **Persistence with Room:**  
   - App status is stored using Room Database, ensuring data persistence even after app restarts.  


### 📝 **Project Structure**

```
📂 App-Approval-System
├── 📂 app
│   ├── 📂 src
│   │   ├── 📂 main
│   │   │   ├── 📂 java/com/example/appapproval
│   │   │   │   ├── 📂 data
│   │   │   │   ├── 📂 ui
│   │   │   │   ├── 📂 utils
│   │   │   └── 📂 res
│   │   │       ├── 📂 drawable
│   │   │       ├── 📂 layout
│   │   │       ├── 📂 values



Feel free to contribute to the project! You can:  
- ⭐️ Star the repo  
- 🐛 Report bugs  
- 🚀 Submit pull requests  

