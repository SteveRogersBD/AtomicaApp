# **Atomica: Empowering Chemistry-Driven Minds**  

## **🌍 Inspiration 🔬**  

Chemistry is the foundation of countless scientific breakthroughs, yet many students struggle to access engaging, interactive resources or connect with a global community of learners. Atomica aims to bridge this gap by creating an Android app that combines cutting-edge AI tools, a dynamic social platform, and immersive learning features. Our goal is to inspire curiosity, foster collaboration, and make chemistry accessible and exciting for students worldwide.

## **What it does**  
1. **📰📹 Stay updated with trending content**: Explore the latest chemistry news, articles, and YouTube videos curated on your personalized feed.  
2. **🌐💬 Connect via social threads**: Join a global community of chemistry enthusiasts, share ideas, ask questions, and get help from peers worldwide.  
3. **🧪✨ Interactive 3D periodic table**: Visualize elements in 3D for an engaging and hands-on learning experience.  
4. **🔍📚 Search any element**: Use the search bar to dive deep into detailed information about any chemical element instantly.  
5. **🤖📝 AI-powered testing & feedback**: Take chemistry quizzes, get instant scoring, and receive personalized AI-driven feedback to improve.  
6. **💬🧠 Chat with an AI assistant**: Ask the AI chatbot anything about chemistry—concepts, reactions, or study tips—and get clear, concise answers.  

---

## **How we built it**  

We developed Atomica by leveraging a mix of modern tools, frameworks, and APIs to create a seamless and powerful learning experience:  

- **Google News API & YouTube Data API**: Integrated to fetch trending chemistry-related news, articles, and videos for the feed.  
- **Google Gemini API**: Powers the AI chatbot, enabling students to ask questions and receive insightful answers.  
- **Three.js**: Used to build the interactive 3D periodic table for an immersive visual experience.  
- **Android Studio**: The core platform for designing and developing the Android app.  
- **Firebase**: Handles real-time data storage, user authentication, and social thread functionality.  
- **TensorFlow Lite**: Deployed for lightweight, on-device AI models for testing, scoring, and feedback.  
- **Flask**: Runs the backend server, connecting the app to AI models and external APIs.  
- **Spring Boot**: Powers the server-side logic, linked to a MySQL database for storing user data and quiz results.  
- **VS Code**: Used for coding, debugging, and testing APIs.  
- **PubChem Dataset**: Sourced detailed chemical element data for the search feature.  

## **Challenges we ran into**  

- **API Limits**: Managing rate limits from multiple APIs (e.g., YouTube, Google News) while ensuring a smooth user experience was tricky.  
- **3D Rendering**: Optimizing the 3D periodic table for performance on various Android devices without compromising quality took extra effort.  
- **AI Accuracy**: Fine-tuning the AI chatbot and testing models to provide accurate, chemistry-specific responses required multiple iterations.  
- **Social Integration**: Building a secure and scalable social thread feature that encourages meaningful interaction posed design and backend challenges.  

## **Accomplishments & What we learned**  

- **App Development**: We successfully built a feature-rich Android app, integrating a 3D periodic table, AI tools, and a social platform—balancing functionality with an intuitive interface.  
- **Team Synergy**: Coordinating between frontend, backend, and AI teams taught us the value of clear communication and shared goals.  
- **Creative Problem-Solving**: We learned to optimize resource-heavy features (like 3D rendering and AI) for mobile devices, enhancing our technical adaptability.  

---

## **What's next for Atomica**  

- **🧪 Virtual Lab Simulations**: We plan to add interactive chemistry experiments using AR/VR for hands-on learning.  
- **🌍 Expanded Content**: Broaden the feed to include podcasts, research papers, and live webinars from chemistry experts.  
- **🤖 Enhanced AI**: Improve the chatbot and testing models to cover advanced topics like organic chemistry and reaction balancing.  
- **📱 Cross-Platform Support**: Extend Atomica to iOS and web platforms to reach more students globally.
