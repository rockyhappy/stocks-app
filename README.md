# Quantify - Stock Trading Android App

A modern Android stock trading application built with Jetpack Compose that provides real-time stock
market data, watchlist management, and detailed stock analysis. This app follows Clean Architecture
principles with MVVM pattern and implements modern Android development best practices.

## 🎥 Demo Videos


https://github.com/user-attachments/assets/7f6a0712-c39b-4836-ab51-e9b46207e045



## 📱 Screenshots

### Light Mode

<img src="https://github.com/user-attachments/assets/a16cadcc-242e-468e-9ffd-7ab7c3a4bd94" alt="Ken Sample" width="150" >
<img src="https://github.com/user-attachments/assets/1eaea0fa-51c6-4c0b-b52d-356169fb34a2" alt="Ken Sample" width="150" >
<img src="https://github.com/user-attachments/assets/b58b97d9-bcb3-4800-8e4b-5a53cf49a04f" alt="Ken Sample" width="150" >
<img src="https://github.com/user-attachments/assets/1f7300f0-6a2e-463b-8eb5-0d80c351a036" alt="Ken Sample" width="150" >
<img src="https://github.com/user-attachments/assets/4429d8b1-af17-4f0c-9ca8-c0d8fcc24a87" alt="Ken Sample" width="150" >
<img src="https://github.com/user-attachments/assets/ee06cb6d-c957-4396-8ddc-4e9a0159fafb" alt="Ken Sample" width="150" >
<img src="https://github.com/user-attachments/assets/626dfc61-0a76-4f38-a4bb-7f22fc7192cd" alt="Ken Sample" width="150" >
<img src="https://github.com/user-attachments/assets/d701e1d6-f26e-4980-a131-eee6bbe876c5" alt="Ken Sample" width="150" >
<img src="https://github.com/user-attachments/assets/4c7d4f8d-98c3-494e-8e6b-898db1b5b6a7" alt="Ken Sample" width="150" >
<img src="https://github.com/user-attachments/assets/2dc5e9fc-d156-4f54-b0a8-f840a5c3afa4" alt="Ken Sample" width="150" >
<img src="https://github.com/user-attachments/assets/6a8c66a2-6c94-4699-bbf1-d31c3d609bd6" alt="Ken Sample" width="150" >
<img src="https://github.com/user-attachments/assets/9de6d1b1-9627-412d-acd5-060c8547f4d3" alt="Ken Sample" width="150" >




### Dark Mode


<img src="https://github.com/user-attachments/assets/ad004384-f3fc-4c27-a0bd-736637b23167" alt="Ken Sample" width="150" >
<img src="https://github.com/user-attachments/assets/ced37823-e95f-44b1-97c0-54d95c541f27" alt="Ken Sample" width="150" >
<img src="https://github.com/user-attachments/assets/dcd94c5e-4b12-4e2e-8cda-0f364bacda0c" alt="Ken Sample" width="150" >
<img src="https://github.com/user-attachments/assets/2656c704-db5c-4724-9411-bdd5c44aa7fe" alt="Ken Sample" width="150" >
<img src="https://github.com/user-attachments/assets/5be2404d-180e-4d9d-885c-eae734a6c53b" alt="Ken Sample" width="150" >
<img src="https://github.com/user-attachments/assets/f5f31beb-2613-49af-b7ef-21f337c49411" alt="Ken Sample" width="150" >
<img src="https://github.com/user-attachments/assets/f6155581-7a3d-4144-84e1-0e241762674b" alt="Ken Sample" width="150" >
<img src="https://github.com/user-attachments/assets/098b7815-b39a-41c4-b45b-39fe5790e350" alt="Ken Sample" width="150" >
<img src="https://github.com/user-attachments/assets/acd6c3b6-0ccd-4856-b0c8-bb4a7bbd8502" alt="Ken Sample" width="150" >
<img src="https://github.com/user-attachments/assets/085db5f0-5785-4d22-a4fc-26826d1dda03" alt="Ken Sample" width="150" >
<img src="https://github.com/user-attachments/assets/cb26f2d3-be38-4352-9eec-d13d87654fad" alt="Ken Sample" width="150" >
<img src="https://github.com/user-attachments/assets/9f97c744-748e-44c6-bfad-ecddc367acf5" alt="Ken Sample" width="150" >
<img src="https://github.com/user-attachments/assets/2930a55f-56e0-4676-8f0e-e64f5d578435" alt="Ken Sample" width="150" >




## 📱 Features

- **Real-time Stock Data**: Live stock prices with gainers, losers, and active stocks
- **Stock Search**: Advanced search functionality with real-time suggestions
- **Watchlist Management**: Add/remove stocks to personal watchlist with local persistence
- **Detailed Stock Analysis**: Comprehensive stock details with charts and OHLCV data
- **Dark/Light Theme**: Dynamic theme support with system preference detection
- **Offline Support**: Local database caching for seamless offline experience
- **Smooth Animations**: Custom navigation animations and transitions

## 🏗️ Architecture

This project implements **Clean Architecture** with **MVVM** pattern:

```
app/
├── presentation/          # UI Layer (Compose UI, ViewModels, Navigation)
├── domain/               # Business Logic Layer
│   ├── models/          # Domain Models
│   ├── repository/      # Repository Interfaces
│   └── usecases/        # Business Use Cases
├── data/                # Data Layer
│   ├── local/          # Room Database, DataStore
│   ├── remote/         # Retrofit API, DTOs
│   └── repository/     # Repository Implementations
├── di/                  # Dependency Injection (Hilt)
├── ui/                  # UI Theme and Common Components
└── utility/             # Constants and Utilities
```

## 🚀 Tech Stack

### Core Technologies

- **Kotlin** - Primary programming language
- **Jetpack Compose** - Modern UI toolkit
- **Coroutines & Flow** - Asynchronous programming
- **Hilt** - Dependency injection
- **Navigation Compose** - Navigation component

### Data & Storage

- **Room Database** - Local data persistence
- **DataStore** - Preferences storage
- **Retrofit** - HTTP client for API calls
- **Gson** - JSON serialization

### UI & UX

- **Material3** - Material Design 3 components
- **Coil** - Image loading library
- **Accompanist** - Compose utilities
- **MPAndroidChart** - Stock charts
- **Shimmer** - Loading animations

### Development Tools

- **Chucker** - Network debugging
- **Timber** - Logging
- **SDP** - Scalable size units

## 📂 Project Structure

### Presentation Layer (`presentation/`)

- `screens/` - All UI screens organized by feature
  - `home/` - Home screen with stock overview
  - `search/` - Stock search functionality
  - `details/` - Detailed stock information
  - `watchlist/` - User's watchlist management
  - `displayList/` - Stock list displays (gainers/losers)
  - `bottomBar/` - Bottom navigation container
- `navigation/` - Navigation configuration and routes

### Domain Layer (`domain/`)

- `models/` - Core business models (Stock, CompanyDetails, etc.)
- `repository/` - Repository contracts
- `usecases/` - Business logic use cases

### Data Layer (`data/`)

- `local/` - Room database, DAOs, entities
- `remote/` - API services, DTOs, mappers
- `repository/` - Repository implementations

## 🔄 Data Flow

### 1. Remote Data Flow

```
API Service → DTO → Mapper → Domain Model → Repository → UseCase → ViewModel → UI State → Compose UI
```

### 2. Local Data Flow

```
Room Database → Entity → Mapper → Domain Model → Repository → UseCase → ViewModel → UI State → Compose UI
```

### 3. User Action Flow

```
User Interaction → Compose UI → ViewModel → UseCase → Repository → Data Source → Response → UI Update
```

## 🧭 Navigation Structure

The app uses **Navigation Compose** with the following navigation graph:

```
MainNavGraph
├── BottomBarScreen (Main Container)
│   ├── HomeScreen (Home tab)
│   ├── SearchScreen (Search tab)
│   └── WatchlistScreen (Watchlist tab)
├── DetailsScreen (Stock details)
└── DisplayScreen (Stock lists - gainers/losers/active)
```

### Navigation Routes

- `HOME_ROUTE` - Main dashboard with stock overview
- `SEARCH_ROUTE` - Stock search and discovery
- `WAITLIST_ROUTE` - Personal watchlist management
- `DETAIL_ROUTE` - Individual stock details with charts
- `DISPLAY_ROUTE` - Categorized stock lists
- `BOTTOM_BAR_ROUTE` - Bottom navigation container

## 🎨 UI Components

### Key Screens

1. **Home Screen** - Dashboard with market overview and quick actions
2. **Search Screen** - Real-time stock search with suggestions
3. **Details Screen** - Comprehensive stock analysis with charts
4. **Watchlist Screen** - Personal stock portfolio management
5. **Display Screen** - Categorized stock lists (gainers, losers, most active)

### Shared Components

- Custom animated transitions between screens
- Reusable stock item cards
- Loading states with shimmer effects
- Error handling UI components

## 🛠️ Setup Instructions

### Prerequisites

- Android Studio Arctic Fox or later
- JDK 11 or higher
- Android SDK 26 or higher

### Installation

1. Clone the repository

```bash
git clone https://github.com/rockyhappy/stocks-app.git
```

2. Open project in Android Studio

3. Add your API KEY of Alphavantage Stocks API in the CONSTANTS:

```properties
# Add your stock API key here
API_KEY=your_api_key_here
```

4. Build and run the project

## 🔧 Configuration

### Theme Configuration

The app supports dynamic theming with three modes:

- **Light Mode** - Traditional light theme
- **Dark Mode** - Dark theme for low-light environments
- **System** - Follows system theme preference

### API Configuration

Configure your stock data API in the network module. The app uses REST APIs for real-time stock
data.

## 📊 Features Deep Dive

### Stock Data Management

- Real-time price updates
- Historical data with OHLCV charts
- Volume and market cap information
- Price change indicators

### Watchlist Features

- Add/remove stocks from watchlist
- Persistent local storage
- Real-time price updates for watchlist items
- Sorting and filtering options

### Search Functionality

- Real-time search suggestions
- Search by ticker symbol or company name
- Search history and favorites
- Advanced filtering options


## 👨‍💻 Developer

**Rachit Katiyar (@devrachit)**

- Email: [rachit22153019@akgec.ac.in]
- LinkedIn: [https://www.linkedin.com/in/rachit-katiyar-800378275/]
- GitHub: [https://github.com/rockyhappy/]


