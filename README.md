# Elisha Creation

## Overview

**Elisha Creation** is a Java-based application designed for product or inventory management. It provides core functionality implemented in Java and supports desktop deployment with Ant build tools. The project structure follows best practices for Java desktop or enterprise applications.

## Features

- Java-based core logic
- Organized source code under `src/com`
- Product/image management (see `Product Images` folder)
- Build automation via `build.xml` (Ant)
- Bundled libraries in `lib/`
- Documentation/report included (`report.pdf`)

## Project Structure

```
.
├── .idea/             # IDE settings
├── backup/            # Backup files
├── build/             # Build output
├── dist/              # Distribution packages
├── lib/               # External libraries
├── nbproject/         # NetBeans project files
├── Product Images/    # Images for products
├── src/               # Main source code (see src/com)
│   └── com/
├── build.xml          # Ant build script
├── Elisha.iml         # IntelliJ IDEA module file
├── manifest.mf        # Java manifest file
├── report.pdf         # Project documentation/report
├── elisha.log         # Log file
└── README.md
```

## Getting Started

1. **Requirements:**
   - Java JDK 8 or higher
   - Apache Ant (for building)

2. **Build and Run:**
   - To build, run:  
     ```
     ant
     ```
   - To run the application, execute the generated JAR in the `dist/` directory.

3. **Configuration:**
   - Update configuration files as needed in the `src` or project root.

4. **Product Images:**
   - Place or update product images in the `Product Images/` folder.

## Documentation

For more details, see `report.pdf`.

## License

Add your license information here.

---

© 2025 [Sanuga7](https://github.com/Sanuga7)
