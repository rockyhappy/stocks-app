# Groww - Stock Trading Android App

A modern Android stock trading application built with Jetpack Compose that provides real-time stock
market data, watchlist management, and detailed stock analysis. This app follows Clean Architecture
principles with MVVM pattern and implements modern Android development best practices.

## 📱 Screenshots

### Light Mode

<!-- Add light mode screenshots here -->
*Screenshots will be added here*

### Dark Mode

<!-- Add dark mode screenshots here -->
*Screenshots will be added here*

## 🎥 Demo Videos

<!-- Add demo videos here -->
*Demo videos will be added here*

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


