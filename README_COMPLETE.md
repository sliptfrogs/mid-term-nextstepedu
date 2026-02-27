# 🚀 Next Step Edu — Complete API Documentation

**Production URL:**
https://mid-term-nextstepedu-production.up.railway.app

---

## 📌 Base URL Rules

### 🔐 Authentication Endpoints

```
https://mid-term-nextstepedu-production.up.railway.app/api/v1/auth
```

### 🌍 All Other Endpoints

```
https://mid-term-nextstepedu-production.up.railway.app/api/v1
```

**Example:**

```
GET https://mid-term-nextstepedu-production.up.railway.app/api/v1/scholarship?page=0&size=10&sortDir=desc
```

---

# 🔐 Authentication (3 endpoints)

## 1️⃣ Register User

**Endpoint:** `POST /api/v1/auth/register`
**Authorization:** Public (no token required)
**Content-Type:** `multipart/form-data`

### Parameters

| Name      | Type | Required | Description              |
| --------- | ---- | -------- | ------------------------ |
| email     | text | ✅       | User email address       |
| password  | text | ✅       | User password            |
| firstname | text | ✅       | First name               |
| lastname  | text | ✅       | Last name                |
| phone     | text | ✅       | Phone number             |
| image     | file | ❌       | Profile image (optional) |

### Response (201)

```
Registered successfully
```

---

## 2️⃣ Login User

**Endpoint:** `POST /api/v1/auth/login`
**Authorization:** Public (no token required)
**Content-Type:** `application/json`

### Request Body

```json
{
  "email": "user@gmail.com",
  "password": "yourPassword123"
}
```

### Response (200)

```json
{
  "accessToken": "eyJhbGc...",
  "refreshToken": "eyJyZW..."
}
```

### Usage in Protected Endpoints

```
Authorization: Bearer <accessToken>
```

---

## 3️⃣ Refresh Token

**Endpoint:** `POST /api/v1/auth/refresh-token`
**Authorization:** Optional
**Content-Type:** `application/json`

### Request Body

```json
{
  "refreshToken": "eyJyZW..."
}
```

### Response (200)

```json
{
  "accessToken": "eyJhbGc...",
  "refreshToken": "eyJyZW..."
}
```

---

# ☁️ Cloudinary Upload (1 endpoint)

**Endpoint Base:** `/api/v1/cloud/upload`

## Upload Image

**Endpoint:** `POST /api/v1/cloud/upload`
**Authorization:** Public (optional)
**Content-Type:** `multipart/form-data`

### Parameters

| Name  | Type | Required | Description          |
| ----- | ---- | -------- | -------------------- |
| image | file | ✅       | Image file to upload |

### Response (200)

```json
{
  "public_id": "cloud_upload/xyz...",
  "secure_url": "https://res.cloudinary.com/...",
  "url": "http://res.cloudinary.com/...",
  "width": 1920,
  "height": 1080,
  ...additional Cloudinary metadata...
}
```

---

# 🎓 Scholarship API (6 endpoints)

**Endpoint Base:** `/api/v1/scholarship`

| Method | Path           | Auth | Description          |
| ------ | -------------- | ---- | -------------------- |
| POST   | `/`            | ✅   | Create scholarship   |
| GET    | `/`            | ❌   | List all (paginated) |
| GET    | `/{id}`        | ❌   | Get by ID            |
| GET    | `/slug/{slug}` | ❌   | Get by slug          |
| PUT    | `/{id}`        | ✅   | Update scholarship   |
| DELETE | `/{id}`        | ✅   | Delete scholarship   |

---

### ✨ POST `/api/v1/scholarship` - Create

**Authorization:** Bearer Token required
**Content-Type:** `multipart/form-data`

#### Parameters

| Name       | Type        | Required | Description              |
| ---------- | ----------- | -------- | ------------------------ |
| data       | JSON string | ✅       | Scholarship data as JSON |
| logo       | file        | ❌       | Logo image               |
| coverImage | file        | ❌       | Cover image              |

#### Data (JSON) Content

```json
{
  "name": "Scholarship A",
  "description": "Full tuition scholarship",
  "level": 1,
  "maxApplicant": 50,
  "amount": 5000.0,
  "benefits": "Tuition, Stipend",
  "requirements": "GPA > 3.5",
  "howToApply": "Apply online",
  "applyLink": "https://example.com/apply",
  "deadline": "2026-03-01T12:00:00",
  "programId": 1,
  "universityId": 1,
  "status": "ACTIVE"
}
```

#### Response (201)

```json
{
  "status": "success",
  "message": "Scholarship created",
  "data": {...},
  "logoReceived": true,
  "coverImageReceived": false
}
```

---

### 📋 GET `/api/v1/scholarship` - List All

**Authorization:** Public
**Query Parameters:**

| Name    | Type    | Default   | Description               |
| ------- | ------- | --------- | ------------------------- |
| page    | integer | 0         | Page number (0-indexed)   |
| size    | integer | 10        | Items per page            |
| sortBy  | string  | createdAt | Field to sort by          |
| sortDir | string  | desc      | Sort direction (asc/desc) |

**Response (200)**

```json
{
  "content": [
    {
      "id": 1,
      "name": "Scholarship A",
      ...
    }
  ],
  "page": 0,
  "size": 10,
  "totalElements": 100,
  "totalPages": 10,
  "first": true,
  "last": false
}
```

---

### 🔍 GET `/api/v1/scholarship/{id}` - Get by ID

**Authorization:** Public
**Response (200):** Single scholarship object

---

### 🔗 GET `/api/v1/scholarship/slug/{slug}` - Get by Slug

**Authorization:** Public
**Response (200):** Single scholarship object

---

### ✏️ PUT `/api/v1/scholarship/{id}` - Update

**Authorization:** Bearer Token required
**Content-Type:** `multipart/form-data`
Same parameters as POST create (data field required)

**Response (200):** Updated scholarship object

---

### 🗑️ DELETE `/api/v1/scholarship/{id}` - Delete

**Authorization:** Bearer Token required
**Response (204):** No content

---

# 📞 Scholarship Contact API (6 endpoints)

**Endpoint Base:** `/api/v1/scholarship-contact`
**Authorization:** Bearer Token required for all

| Method | Path                           | Description           |
| ------ | ------------------------------ | --------------------- |
| POST   | `/`                            | Create contact        |
| GET    | `/`                            | List all contacts     |
| GET    | `/{id}`                        | Get by ID             |
| GET    | `/scholarship/{scholarshipId}` | Get by scholarship ID |
| PUT    | `/{id}`                        | Update contact        |
| DELETE | `/{id}`                        | Delete contact        |

---

### ✨ POST - Create Contact

**Content-Type:** `application/json`

```json
{
  "label": "Admissions Office",
  "email": "admissions@example.com",
  "phone": "+1234567890",
  "websiteUrl": "https://example.com",
  "scholarshipId": 2
}
```

**Response (201):** Created contact object

---

### 📋 GET Methods

**Response (200):** Contact object(s)

---

### ✏️ PUT `/{id}` - Update

**Content-Type:** `application/json`
Same request body as POST
**Response (200):** Updated contact object

---

### 🗑️ DELETE `/{id}` - Delete

**Response (204):** No content

---

# 🏫 University API (7 endpoints)

**Endpoint Base:** `/api/v1/universities`

| Method | Path           | Auth | Description         |
| ------ | -------------- | ---- | ------------------- |
| POST   | `/`            | ✅   | Create university   |
| GET    | `/`            | ❌   | List all            |
| GET    | `/{id}`        | ❌   | Get by ID           |
| GET    | `/slug/{slug}` | ❌   | Get by slug         |
| GET    | `/search`      | ❌   | Search universities |
| PUT    | `/{id}`        | ✅   | Update university   |
| DELETE | `/{id}`        | ✅   | Delete university   |

---

### ✨ POST `/api/v1/universities` - Create

**Authorization:** Bearer Token required
**Content-Type:** `multipart/form-data`

#### Parameters

| Name            | Type | Required | Description              |
| --------------- | ---- | -------- | ------------------------ |
| name            | text | ✅       | University name          |
| slug            | text | ❌       | URL slug                 |
| description     | text | ❌       | Description              |
| country         | text | ❌       | Country                  |
| city            | text | ❌       | City                     |
| officialWebsite | text | ❌       | Official website URL     |
| status          | text | ❌       | Status (ACTIVE/INACTIVE) |
| email           | text | ❌       | Contact email            |
| label           | text | ❌       | Contact label            |
| phone           | text | ❌       | Contact phone            |
| logo            | file | ❌       | Logo image               |
| coverImage      | file | ❌       | Cover image              |

**Response (201):** Created university object

---

### 📋 GET Methods

**Response (200):** University object(s)

---

### 🔎 GET `/search` - Search

**Query Parameters:**

| Name    | Type   | Required | Description    |
| ------- | ------ | -------- | -------------- |
| keyword | string | ✅       | Search keyword |

**Response (200):** Array of matching universities

---

### ✏️ PUT `/{id}` - Update

**Authorization:** Bearer Token required
**Content-Type:** `multipart/form-data`
All parameters are optional

**Response (200):** Updated university object

---

### 🗑️ DELETE `/{id}` - Delete

**Authorization:** Bearer Token required
**Response (204):** No content

---

# 🏛️ Faculty API (6 endpoints)

**Endpoint Base:** `/api/v1/faculties`
**Authorization:** Bearer Token required for all

| Method | Path                         | Description                             |
| ------ | ---------------------------- | --------------------------------------- |
| POST   | `/`                          | Create faculty                          |
| GET    | `/`                          | List all (optional universityId filter) |
| GET    | `/{id}`                      | Get by ID                               |
| GET    | `/university/{universityId}` | Get by university                       |
| PUT    | `/{id}`                      | Update faculty                          |
| DELETE | `/{id}`                      | Delete faculty                          |

---

### ✨ POST - Create

**Content-Type:** `application/json`

```json
{
  "name": "Faculty of Engineering",
  "description": "Engineering and technology programs"
}
```

**Response (201):** Created faculty object

---

### 📋 GET `/` - List All

**Query Parameters:**

| Name         | Type    | Required | Description          |
| ------------ | ------- | -------- | -------------------- |
| universityId | integer | ❌       | Filter by university |

**Response (200):** Array of faculty objects

---

### 🔍 GET `/{id}` - Get by ID

**Response (200):** Faculty object

---

### 🔎 GET `/university/{universityId}` - Get by University

**Response (200):** Array of faculties

---

### ✏️ PUT `/{id}` - Update

**Content-Type:** `application/json`
Same request body as POST

**Response (200):** Updated faculty object

---

### 🗑️ DELETE `/{id}` - Delete

**Response (204):** No content

---

# 📘 Program API (10 endpoints)

**Endpoint Base:** `/api/v1/programs`
**Authorization:** Bearer Token required for all

| Method | Path                         | Description             |
| ------ | ---------------------------- | ----------------------- |
| POST   | `/`                          | Create program          |
| GET    | `/`                          | List all programs       |
| GET    | `/{id}`                      | Get by ID               |
| GET    | `/university/{universityId}` | Programs by university  |
| GET    | `/faculty/{facultyId}`       | Programs by faculty     |
| GET    | `/search`                    | Search by name          |
| GET    | `/degree-level/{level}`      | Filter by degree level  |
| GET    | `/tuition-range`             | Filter by tuition range |
| PUT    | `/{id}`                      | Update program          |
| DELETE | `/{id}`                      | Delete program          |

---

### ✨ POST - Create

**Content-Type:** `application/json`

```json
{
  "name": "Computer Science",
  "degreeLevel": 3,
  "tuitionFee": 12000.0,
  "universityId": 1,
  "facultyId": 1
}
```

**Response (201):** Created program object

---

### 📋 GET `/` - List All

**Response (200):** Array of programs

---

### 🔍 GET `/{id}` - Get by ID

**Response (200):** Program object

---

### 🔎 GET `/university/{universityId}` - Get by University

**Response (200):** Array of programs

---

### 🔎 GET `/faculty/{facultyId}` - Get by Faculty

**Response (200):** Array of programs

---

### 🔎 GET `/search` - Search by Name

**Query Parameters:**

| Name | Type   | Required | Description  |
| ---- | ------ | -------- | ------------ |
| name | string | ✅       | Program name |

**Response (200):** Array of matching programs

---

### 📊 GET `/degree-level/{level}` - By Degree Level

**Path Parameter:**

| Name  | Type    | Description  |
| ----- | ------- | ------------ |
| level | integer | Degree level |

**Response (200):** Array of programs

---

### 💰 GET `/tuition-range` - By Tuition Range

**Query Parameters:**

| Name | Type    | Required | Description     |
| ---- | ------- | -------- | --------------- |
| min  | decimal | ✅       | Minimum tuition |
| max  | decimal | ✅       | Maximum tuition |

**Response (200):** Array of programs in range

---

### ✏️ PUT `/{id}` - Update

**Content-Type:** `application/json`
Same request body as POST

**Response (200):** Updated program object

---

### 🗑️ DELETE `/{id}` - Delete

**Response (204):** No content

---

# 👤 Applicant API (7 endpoints)

**Endpoint Base:** `/api/v1/applicants`
**Authorization:** Bearer Token required for all

| Method | Path               | Description         |
| ------ | ------------------ | ------------------- |
| POST   | `/`                | Create applicant    |
| GET    | `/`                | List all applicants |
| GET    | `/{id}`            | Get by ID           |
| GET    | `/user/{userId}`   | Get by user ID      |
| GET    | `/status/{status}` | Get by status       |
| PATCH  | `/{id}/status`     | Update status       |
| DELETE | `/{id}`            | Delete applicant    |

---

### ✨ POST - Create

**Content-Type:** `application/json`

```json
{
  "userId": 1,
  "scholarshipId": 1,
  "status": "PENDING"
}
```

**Response (200 or 201):** Created applicant object

---

### 📋 GET `/` - List All

**Response (200):** Array of applicants

---

### 🔍 GET `/{id}` - Get by ID

**Path Parameter:** `id` (Long)
**Response (200):** Applicant object

---

### 👤 GET `/user/{userId}` - Get by User

**Path Parameter:** `userId` (Integer)
**Response (200):** Array of user's applicants

---

### 📊 GET `/status/{status}` - Get by Status

**Path Parameter:** `status` (String)
**Response (200):** Array of applicants with that status

---

### 🔄 PATCH `/{id}/status` - Update Status

**Path Parameter:** `id` (Long)
**Query Parameter:**

| Name   | Type   | Required | Description                            |
| ------ | ------ | -------- | -------------------------------------- |
| status | string | ✅       | New status (PENDING/APPROVED/REJECTED) |

**Response (200):** Updated applicant object

---

### 🗑️ DELETE `/{id}` - Delete

**Response (204):** No content

---

# 👤 User Profile API (3 endpoints)

**Endpoint Base:** `/api/v1/profile`
**Authorization:** Bearer Token required for all

| Method | Path              | Description       |
| ------ | ----------------- | ----------------- |
| GET    | `/`               | List all profiles |
| PUT    | `/users/{userId}` | Update profile    |
| DELETE | `/users/{userId}` | Delete profile    |

---

### 📋 GET `/` - List All

**Response (200):** Array of user profile objects

---

### ✏️ PUT `/users/{userId}` - Update Profile

**Path Parameter:** `userId` (Integer)
**Content-Type:** `multipart/form-data`

#### Parameters

| Name      | Type | Required | Description   |
| --------- | ---- | -------- | ------------- |
| firstname | text | ✅       | First name    |
| lastname  | text | ✅       | Last name     |
| phone     | text | ✅       | Phone number  |
| image     | file | ❌       | Profile image |

**Response (200)**

```
Profile updated
```

---

### 🗑️ DELETE `/users/{userId}` - Delete Profile

**Path Parameter:** `userId` (Integer)
**Response (200)**

```
Profile deleted
```

---

# 👥 User Management API (1 endpoint)

**Endpoint Base:** `/api/v1/users`
**Authorization:** Bearer Token required

| Method | Path           | Description        |
| ------ | -------------- | ------------------ |
| PATCH  | `/{id}/status` | Update user status |

---

### 🔄 PATCH `/{id}/status` - Update Status

**Path Parameter:** `id` (Integer)
**Content-Type:** `application/json`

```json
{
  "status": "ACTIVE"
}
```

**Response (200)**

```
User status updated
```

---

# 📊 API Summary

| Resource            | Endpoints | Bearer Required | Public Available               |
| ------------------- | --------- | --------------- | ------------------------------ |
| Authentication      | 3         | No              | ✅                             |
| Cloudinary          | 1         | No              | ✅                             |
| Scholarship         | 6         | Partial         | Create/Update/Delete need auth |
| Scholarship Contact | 6         | Yes             | ❌                             |
| University          | 7         | Partial         | Create/Update/Delete need auth |
| Faculty             | 6         | Yes             | ❌                             |
| Program             | 10        | Yes             | ❌                             |
| Applicant           | 7         | Yes             | ❌                             |
| User Profile        | 3         | Yes             | ❌                             |
| User Management     | 1         | Yes             | ❌                             |
| **TOTAL**           | **50**    | —               | —                              |

---

# 🧪 Testing Workflow

### Step 1: Register

```
POST /api/v1/auth/register
multipart/form-data: email, password, firstname, lastname, phone, image?
```

### Step 2: Login

```
POST /api/v1/auth/login
json: email, password
↓ Get accessToken
```

### Step 3: Set Authorization Header

```
Authorization: Bearer <accessToken>
```

### Step 4: Create Resources

- Create scholarship
- Create university
- Create faculty
- Create program

### Step 5: Get Resources

- List with pagination
- Get by ID
- Filter/search

### Step 6: Update Resources

```
PUT /resource/{id}
```

### Step 7: Delete Resources

```
DELETE /resource/{id}
```

---

# ⏰ Date & Time Format

All timestamps use **ISO 8601** format:

```
YYYY-MM-DDTHH:mm:ss
```

**Example:** `2026-12-31T23:59:59`

---

# 📝 HTTP Status Codes

| Code | Meaning      | When Used              |
| ---- | ------------ | ---------------------- |
| 200  | OK           | Successful request     |
| 201  | Created      | Resource created       |
| 204  | No Content   | Successful deletion    |
| 400  | Bad Request  | Invalid parameters     |
| 401  | Unauthorized | Missing/invalid token  |
| 404  | Not Found    | Resource doesn't exist |
| 500  | Server Error | Internal server error  |

---

# ✨ Tips

- Always include `Authorization: Bearer <token>` for protected endpoints
- Use `multipart/form-data` for file uploads
- Use `application/json` for JSON payloads
- Pagination defaults to page 0, size 10
- Check response codes and error messages for issues

---

**Happy coding!** 🎉
