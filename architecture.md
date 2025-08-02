graph TD
    subgraph Client
        A[Android App]
    end

    subgraph Backend
        B[API Gateway]
        C[User Service]
        D[Social Service]
        E[Content Service]
        F[AI Service]
    end

    subgraph Data Stores
        G[User DB (PostgreSQL)]
        H[Social DB (PostgreSQL)]
        I[Element Data (Static JSON / NoSQL)]
        J[Vector DB (e.g., Pinecone)]
        K[Cache (Redis)]
    end

    subgraph Third-Party Services
        L[Firebase Authentication]
        M[External News API]
        N[YouTube Data API]
        O[LLM Provider (e.g., Google AI Platform)]
    end

    A -- REST API --> B

    B --> C
    B --> D
    B --> E
    B --> F

    C --- G
    D --- H
    E --- M
    E --- N
    E --- K
    F --- O
    F --- J

    C -.-> L[Uses Firebase Auth]

    style J fill:#f9f,stroke:#333,stroke-width:2px
    style O fill:#f9f,stroke:#333,stroke-width:2px
