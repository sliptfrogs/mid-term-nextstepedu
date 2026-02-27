# 🚀 Next Step Edu — Complete API Documentation

**Production URL:**
https://mid-term-nextstepedu-production.up.railway.app

---

## 📌 Base URL Rules

## 🔐 Authentication Endpoints

```
https://mid-term-nextstepedu-production.up.railway.app/api/v1/auth
```

## 🌍 All Other Endpoints

```
https://mid-term-nextstepedu-production.up.railway.app/api/v1
```

Example:

```
GET https://mid-term-nextstepedu-production.up.railway.app/api/v1/scholarship?page=0&size=10&sortDir=desc
```

---

# 🔐 Authentication

## Register

**POST** `/api/v1/auth/register`
Content-Type: `multipart/form-data`

Fields:

- email (text, required)
- password (text, required)
- firstname (text, required)
- lastname (text, required)
- phone (text, required)
- image (file, optional)

### Example Request:

```
POST /api/v1/auth/register
Content-Type: multipart/form-data

email: user@example.com
password: SecurePassword123!
firstname: John
lastname: Doe
phone: +1234567890
image: [file]
```

### Response (200 OK):

```json
{
  "message": "Registered successfully"
}
```

---

## Login

**POST** `/api/v1/auth/login`
Content-Type: `application/json`

### Request:

```json
{
  "email": "user@example.com",
  "password": "SecurePassword123!"
}
```

### Response (200 OK):

```json
{
  "tokenType": "Bearer",
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

---

## Using Access Token

Include access token in header for protected endpoints:

```
Authorization: Bearer <accessToken>
```

Example:

```
GET /api/v1/scholarship
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

---

## Refresh Token

**POST** `/api/v1/auth/refresh-token`
Content-Type: `application/json`

Request:

```json
{
  "refreshToken": "your_refresh_token"
}
```

Response:

```json
{
  "tokenType": "Bearer",
  "accessToken": "new_access_token",
  "refreshToken": "new_refresh_token"
}
```

---

# ☁️ Cloudinary Upload

Base Endpoint:

```
/api/v1/cloud/upload
```

Requires: Bearer Token (optional, for file uploads)

| Method | Endpoint | Description  |
| ------ | -------- | ------------ |
| POST   | `/`      | Upload image |

### CREATE - POST `/api/v1/cloud/upload`

**Content-Type:** `multipart/form-data`

**Form Field:**

- `image` (file, required) - Image file to upload

**Example Request:**

```
POST /api/v1/cloud/upload
Content-Type: multipart/form-data

image: [file]
```

**Response (200 OK):**

```json
{
  "url": "https://res.cloudinary.com/your-cloud/image/upload/v1234567890/filename.jpg",
  "publicId": "filename",
  "width": 1200,
  "height": 800,
  "format": "jpg",
  "resourceType": "image"
}
```

**Error Response (400 Bad Request):**

```json
{
  "error": "No file provided"
}
```

---

# 🎓 Scholarship API

Base:

```
/api/v1/scholarship
```

Requires: Bearer Token

| Method | Endpoint       | Description        |
| ------ | -------------- | ------------------ |
| POST   | `/`            | Create scholarship |
| GET    | `/`            | List scholarships  |
| GET    | `/slug/{slug}` | Get by slug        |
| GET    | `/{id}`        | Get by ID          |
| PUT    | `/{id}`        | Update scholarship |
| DELETE | `/{id}`        | Delete scholarship |

### CREATE - POST `/api/v1/scholarship`

**Content-Type:** `multipart/form-data`

Form fields:

- `data` (JSON string, required)
- `logo` (file, optional)
- `coverImage` (file, optional)

**Request data:**

```json
{
  "name": "Scholarship A",
  "description": "Full tuition scholarship",
  "level": 1,
  "maxApplicant": 50,
  "amount": 5000,
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

**Response (201 Created):**

```json
{
  "status": "success",
  "message": "Scholarship created",
  "data": {
    "id": 1,
    "name": "Scholarship A",
    "description": "Full tuition scholarship",
    "level": 1,
    "amount": 5000,
    "status": "ACTIVE"
  },
  "logoReceived": true,
  "coverImageReceived": false
}
```

### READ - GET `/api/v1/scholarship` (List with Pagination)

**Query Parameters:**

- `page` (default: 0)
- `size` (default: 10)
- `sortBy` (default: createdAt)
- `sortDir` (default: desc)

**Example Request:**

```
GET /api/v1/scholarship?page=0&size=10&sortDir=desc
Authorization: Bearer <accessToken>
```

**Response (200 OK):**

```json
{
  "content": [
    {
      "id": 1,
      "name": "Scholarship A",
      "description": "Full tuition scholarship",
      "level": 1,
      "amount": 5000,
      "status": "ACTIVE"
    }
  ],
  "page": 0,
  "size": 10,
  "totalElements": 1,
  "totalPages": 1,
  "first": true,
  "last": true
}
```

### READ - GET `/api/v1/scholarship/{id}` (Get by ID)

**Example Request:**

```
GET /api/v1/scholarship/1
Authorization: Bearer <accessToken>
```

**Response (200 OK):**

```json
{
  "id": 1,
  "name": "Scholarship A",
  "description": "Full tuition scholarship",
  "level": 1,
  "amount": 5000,
  "status": "ACTIVE",
  "deadline": "2026-03-01T12:00:00"
}
```

### READ - GET `/api/v1/scholarship/slug/{slug}` (Get by Slug)

**Example Request:**

```
GET /api/v1/scholarship/slug/scholarship-a
Authorization: Bearer <accessToken>
```

### UPDATE - PUT `/api/v1/scholarship/{id}`

**Content-Type:** `multipart/form-data`

**Request data:**

```json
{
  "name": "Scholarship A Updated",
  "description": "Updated description",
  "level": 2,
  "amount": 7000,
  "status": "ACTIVE"
}
```

**Response (200 OK):**

```json
{
  "id": 1,
  "name": "Scholarship A Updated",
  "description": "Updated description",
  "level": 2,
  "amount": 7000,
  "status": "ACTIVE"
}
```

### DELETE - DELETE `/api/v1/scholarship/{id}`

**Example Request:**

```
DELETE /api/v1/scholarship/1
Authorization: Bearer <accessToken>
```

**Response (204 No Content):**

```
(empty body)
```

---

# 📞 Scholarship Contact API

Base:

```
/api/v1/scholarship-contact
```

Requires: Bearer Token

| Method | Endpoint            | Description           |
| ------ | ------------------- | --------------------- |
| POST   | `/`                 | Create contact        |
| GET    | `/`                 | List all contacts     |
| GET    | `/{id}`             | Get contact by ID     |
| GET    | `/scholarship/{id}` | Get by scholarship ID |
| PUT    | `/{id}`             | Update contact        |
| DELETE | `/{id}`             | Delete contact        |

### CREATE - POST `/api/v1/scholarship-contact`

**Content-Type:** `application/json`

**Request:**

```json
{
  "label": "Admissions Office",
  "email": "admissions@example.com",
  "phone": "+1234567890",
  "websiteUrl": "https://example.com",
  "scholarshipId": 1
}
```

**Response (201 Created):**

```json
{
  "id": 1,
  "label": "Admissions Office",
  "email": "admissions@example.com",
  "phone": "+1234567890",
  "websiteUrl": "https://example.com",
  "scholarshipId": 1
}
```

### READ - GET `/api/v1/scholarship-contact` (List All)

**Example Request:**

```
GET /api/v1/scholarship-contact
Authorization: Bearer <accessToken>
```

**Response (200 OK):**

```json
[
  {
    "id": 1,
    "label": "Admissions Office",
    "email": "admissions@example.com",
    "phone": "+1234567890",
    "websiteUrl": "https://example.com",
    "scholarshipId": 1
  },
  {
    "id": 2,
    "label": "Financial Aid",
    "email": "finaid@example.com",
    "phone": "+1234567891",
    "websiteUrl": "https://example.com/finaid",
    "scholarshipId": 1
  }
]
```

### READ - GET `/api/v1/scholarship-contact/{id}` (Get by ID)

**Example Request:**

```
GET /api/v1/scholarship-contact/1
Authorization: Bearer <accessToken>
```

**Response (200 OK):**

```json
{
  "id": 1,
  "label": "Admissions Office",
  "email": "admissions@example.com",
  "phone": "+1234567890",
  "websiteUrl": "https://example.com",
  "scholarshipId": 1
}
```

### READ - GET `/api/v1/scholarship-contact/scholarship/{scholarshipId}` (Get by Scholarship)

**Example Request:**

```
GET /api/v1/scholarship-contact/scholarship/1
Authorization: Bearer <accessToken>
```

### UPDATE - PUT `/api/v1/scholarship-contact/{id}`

**Content-Type:** `application/json`

**Request:**

```json
{
  "label": "Admissions Office Updated",
  "email": "new-email@example.com",
  "phone": "+9876543210",
  "websiteUrl": "https://newexample.com",
  "scholarshipId": 1
}
```

**Response (200 OK):**

```json
{
  "id": 1,
  "label": "Admissions Office Updated",
  "email": "new-email@example.com",
  "phone": "+9876543210",
  "websiteUrl": "https://newexample.com",
  "scholarshipId": 1
}
```

### DELETE - DELETE `/api/v1/scholarship-contact/{id}`

**Example Request:**

```
DELETE /api/v1/scholarship-contact/1
Authorization: Bearer <accessToken>
```

**Response (204 No Content):**

```
(empty body)
```

---

# 🏫 University API

Base:

```
/api/v1/universities
```

Requires: Bearer Token (for modifications)

| Method | Endpoint       | Description           |
| ------ | -------------- | --------------------- |
| POST   | `/`            | Create university     |
| GET    | `/`            | List all universities |
| GET    | `/{id}`        | Get by ID             |
| GET    | `/slug/{slug}` | Get by slug           |
| GET    | `/search`      | Search by keyword     |
| PUT    | `/{id}`        | Update university     |
| DELETE | `/{id}`        | Delete university     |

### CREATE - POST `/api/v1/universities`

**Content-Type:** `multipart/form-data`

**Form Fields:**

- `name` (text, required)
- `slug` (text, optional)
- `description` (text, optional)
- `country` (text, optional)
- `city` (text, optional)
- `officialWebsite` (text, optional)
- `status` (text, optional)
- `email` (text, optional)
- `label` (text, optional)
- `phone` (text, optional)
- `logo` (file, optional)
- `coverImage` (file, optional)

**Example Form Data:**

```
name: "Harvard University"
slug: "harvard-university"
description: "Prestigious university in Massachusetts"
country: "USA"
city: "Cambridge"
officialWebsite: "https://harvard.edu"
status: "ACTIVE"
email: "admissions@harvard.edu"
phone: "+1234567890"
logo: [file]
coverImage: [file]
```

**Response (201 Created):**

```json
{
  "id": 1,
  "name": "Harvard University",
  "slug": "harvard-university",
  "description": "Prestigious university in Massachusetts",
  "country": "USA",
  "city": "Cambridge",
  "officialWebsite": "https://harvard.edu",
  "status": "ACTIVE"
}
```

### READ - GET `/api/v1/universities` (List All)

**Example Request:**

```
GET /api/v1/universities
Authorization: Bearer <accessToken>
```

**Response (200 OK):**

```json
[
  {
    "id": 1,
    "name": "Harvard University",
    "slug": "harvard-university",
    "country": "USA",
    "city": "Cambridge",
    "status": "ACTIVE"
  },
  {
    "id": 2,
    "name": "MIT",
    "slug": "mit",
    "country": "USA",
    "city": "Cambridge",
    "status": "ACTIVE"
  }
]
```

### READ - GET `/api/v1/universities/{id}` (Get by ID)

**Example Request:**

```
GET /api/v1/universities/1
Authorization: Bearer <accessToken>
```

**Response (200 OK):**

```json
{
  "id": 1,
  "name": "Harvard University",
  "slug": "harvard-university",
  "description": "Prestigious university in Massachusetts",
  "country": "USA",
  "city": "Cambridge",
  "officialWebsite": "https://harvard.edu",
  "status": "ACTIVE",
  "email": "admissions@harvard.edu",
  "phone": "+1234567890"
}
```

### READ - GET `/api/v1/universities/slug/{slug}` (Get by Slug)

**Example Request:**

```
GET /api/v1/universities/slug/harvard-university
Authorization: Bearer <accessToken>
```

### READ - GET `/api/v1/universities/search` (Search)

**Query Parameter:**

- `keyword` (required)

**Example Request:**

```
GET /api/v1/universities/search?keyword=Harvard
Authorization: Bearer <accessToken>
```

### UPDATE - PUT `/api/v1/universities/{id}`

**Content-Type:** `multipart/form-data`

**Form Fields** (all optional):

- All CREATE fields available

**Example Form Data:**

```
name: "Harvard University Updated"
description: "Updated description"
status: "INACTIVE"
phone: "+9876543210"
```

**Response (200 OK):**

```json
{
  "id": 1,
  "name": "Harvard University Updated",
  "description": "Updated description",
  "status": "INACTIVE",
  "phone": "+9876543210"
}
```

### DELETE - DELETE `/api/v1/universities/{id}`

**Example Request:**

```
DELETE /api/v1/universities/1
Authorization: Bearer <accessToken>
```

**Response (204 No Content):**

```
(empty body)
```

---

# 🏛 Faculty API

Base:

```
/api/v1/faculties
```

Requires: Bearer Token

| Method | Endpoint           | Description          |
| ------ | ------------------ | -------------------- |
| POST   | `/`                | Create faculty       |
| GET    | `/`                | List all faculties   |
| GET    | `/{id}`            | Get by ID            |
| GET    | `/university/{id}` | Get by university ID |
| PUT    | `/{id}`            | Update faculty       |
| DELETE | `/{id}`            | Delete faculty       |

### CREATE - POST `/api/v1/faculties`

**Content-Type:** `application/json`

**Request:**

```json
{
  "name": "Faculty of Engineering",
  "description": "Engineering and technology programs"
}
```

**Response (201 Created):**

```json
{
  "id": 1,
  "name": "Faculty of Engineering",
  "description": "Engineering and technology programs"
}
```

### READ - GET `/api/v1/faculties` (List All)

**Query Parameters:**

- `universityId` (optional) - filter by university

**Example Request:**

```
GET /api/v1/faculties
Authorization: Bearer <accessToken>
```

Or with filter:

```
GET /api/v1/faculties?universityId=1
Authorization: Bearer <accessToken>
```

**Response (200 OK):**

```json
[
  {
    "id": 1,
    "name": "Faculty of Engineering",
    "description": "Engineering and technology programs"
  },
  {
    "id": 2,
    "name": "Faculty of Science",
    "description": "Natural sciences programs"
  }
]
```

### READ - GET `/api/v1/faculties/{id}` (Get by ID)

**Example Request:**

```
GET /api/v1/faculties/1
Authorization: Bearer <accessToken>
```

**Response (200 OK):**

```json
{
  "id": 1,
  "name": "Faculty of Engineering",
  "description": "Engineering and technology programs"
}
```

### READ - GET `/api/v1/faculties/university/{universityId}` (Get by University)

**Example Request:**

```
GET /api/v1/faculties/university/1
Authorization: Bearer <accessToken>
```

### UPDATE - PUT `/api/v1/faculties/{id}`

**Content-Type:** `application/json`

**Request:**

```json
{
  "name": "Faculty of Engineering Updated",
  "description": "Updated engineering and technology programs"
}
```

**Response (200 OK):**

```json
{
  "id": 1,
  "name": "Faculty of Engineering Updated",
  "description": "Updated engineering and technology programs"
}
```

### DELETE - DELETE `/api/v1/faculties/{id}`

**Example Request:**

```
DELETE /api/v1/faculties/1
Authorization: Bearer <accessToken>
```

**Response (204 No Content):**

```
(empty body)
```

---

# 📘 Program API

Base:

```
/api/v1/programs
```

Requires: Bearer Token

| Method | Endpoint                             | Description             |
| ------ | ------------------------------------ | ----------------------- |
| POST   | `/`                                  | Create program          |
| GET    | `/`                                  | List all programs       |
| GET    | `/{id}`                              | Get by ID               |
| GET    | `/university/{id}`                   | Get by university       |
| GET    | `/faculty/{id}`                      | Get by faculty          |
| GET    | `/search?name={name}`                | Search by name          |
| GET    | `/degree-level/{level}`              | Get by degree level     |
| GET    | `/tuition-range?min={min}&max={max}` | Filter by tuition range |
| PUT    | `/{id}`                              | Update program          |
| DELETE | `/{id}`                              | Delete program          |

### CREATE - POST `/api/v1/programs`

**Content-Type:** `application/json`

**Request:**

```json
{
  "name": "Computer Science",
  "description": "Bachelor of Science in Computer Science",
  "degreeLevel": 3,
  "tuitionFee": 15000,
  "universityId": 1,
  "facultyId": 1
}
```

**Response (201 Created):**

```json
{
  "id": 1,
  "name": "Computer Science",
  "description": "Bachelor of Science in Computer Science",
  "degreeLevel": 3,
  "tuitionFee": 15000,
  "universityId": 1,
  "facultyId": 1
}
```

### READ - GET `/api/v1/programs` (List All)

**Example Request:**

```
GET /api/v1/programs
Authorization: Bearer <accessToken>
```

**Response (200 OK):**

```json
[
  {
    "id": 1,
    "name": "Computer Science",
    "description": "Bachelor of Science in Computer Science",
    "degreeLevel": 3,
    "tuitionFee": 15000
  },
  {
    "id": 2,
    "name": "Business Administration",
    "description": "Bachelor of Business Administration",
    "degreeLevel": 3,
    "tuitionFee": 12000
  }
]
```

### READ - GET `/api/v1/programs/{id}` (Get by ID)

**Example Request:**

```
GET /api/v1/programs/1
Authorization: Bearer <accessToken>
```

**Response (200 OK):**

```json
{
  "id": 1,
  "name": "Computer Science",
  "description": "Bachelor of Science in Computer Science",
  "degreeLevel": 3,
  "tuitionFee": 15000,
  "universityId": 1,
  "facultyId": 1
}
```

### READ - GET `/api/v1/programs/university/{universityId}` (Get by University)

**Example Request:**

```
GET /api/v1/programs/university/1
Authorization: Bearer <accessToken>
```

### READ - GET `/api/v1/programs/faculty/{facultyId}` (Get by Faculty)

**Example Request:**

```
GET /api/v1/programs/faculty/1
Authorization: Bearer <accessToken>
```

### READ - GET `/api/v1/programs/search` (Search by Name)

**Query Parameter:**

- `name` (required)

**Example Request:**

```
GET /api/v1/programs/search?name=Computer
Authorization: Bearer <accessToken>
```

### READ - GET `/api/v1/programs/degree-level/{level}` (Get by Degree Level)

**Example Request:**

```
GET /api/v1/programs/degree-level/3
Authorization: Bearer <accessToken>
```

### READ - GET `/api/v1/programs/tuition-range` (Filter by Tuition Range)

**Query Parameters:**

- `min` (required)
- `max` (required)

**Example Request:**

```
GET /api/v1/programs/tuition-range?min=10000&max=20000
Authorization: Bearer <accessToken>
```

### UPDATE - PUT `/api/v1/programs/{id}`

**Content-Type:** `application/json`

**Request:**

```json
{
  "name": "Computer Science Updated",
  "description": "Updated program description",
  "degreeLevel": 3,
  "tuitionFee": 16000,
  "universityId": 1,
  "facultyId": 1
}
```

**Response (200 OK):**

```json
{
  "id": 1,
  "name": "Computer Science Updated",
  "description": "Updated program description",
  "degreeLevel": 3,
  "tuitionFee": 16000,
  "universityId": 1,
  "facultyId": 1
}
```

### DELETE - DELETE `/api/v1/programs/{id}`

**Example Request:**

```
DELETE /api/v1/programs/1
Authorization: Bearer <accessToken>
```

**Response (204 No Content):**

```
(empty body)
```

---

# 👤 Applicant API

Base:

```
/api/v1/applicants
```

Requires: Bearer Token

| Method | Endpoint           | Description         |
| ------ | ------------------ | ------------------- |
| POST   | `/`                | Create applicant    |
| GET    | `/`                | List all applicants |
| GET    | `/{id}`            | Get by ID           |
| GET    | `/user/{id}`       | Get by user ID      |
| GET    | `/status/{status}` | Get by status       |
| PATCH  | `/{id}/status`     | Update status       |
| DELETE | `/{id}`            | Delete applicant    |

### CREATE - POST `/api/v1/applicants`

**Content-Type:** `application/json`

**Request:**

```json
{
  "userId": 1,
  "scholarshipId": 1,
  "programId": 1,
  "status": "PENDING"
}
```

**Response (201 Created):**

```json
{
  "id": 1,
  "userId": 1,
  "scholarshipId": 1,
  "programId": 1,
  "status": "PENDING"
}
```

### READ - GET `/api/v1/applicants` (List All)

**Example Request:**

```
GET /api/v1/applicants
Authorization: Bearer <accessToken>
```

**Response (200 OK):**

```json
[
  {
    "id": 1,
    "userId": 1,
    "scholarshipId": 1,
    "programId": 1,
    "status": "PENDING"
  },
  {
    "id": 2,
    "userId": 2,
    "scholarshipId": 1,
    "programId": 1,
    "status": "ACCEPTED"
  }
]
```

### READ - GET `/api/v1/applicants/{id}` (Get by ID)

**Example Request:**

```
GET /api/v1/applicants/1
Authorization: Bearer <accessToken>
```

**Response (200 OK):**

```json
{
  "id": 1,
  "userId": 1,
  "scholarshipId": 1,
  "programId": 1,
  "status": "PENDING"
}
```

### READ - GET `/api/v1/applicants/user/{userId}` (Get by User ID)

**Example Request:**

```
GET /api/v1/applicants/user/1
Authorization: Bearer <accessToken>
```

### READ - GET `/api/v1/applicants/status/{status}` (Get by Status)

**Path Parameter:**

- `status` (e.g., PENDING, ACCEPTED, REJECTED)

**Example Request:**

```
GET /api/v1/applicants/status/ACCEPTED
Authorization: Bearer <accessToken>
```

### UPDATE - PATCH `/api/v1/applicants/{id}/status` (Update Status)

**Query Parameter:**

- `status` (required)

**Example Request:**

```
PATCH /api/v1/applicants/1/status?status=ACCEPTED
Authorization: Bearer <accessToken>
```

**Response (200 OK):**

```json
{
  "id": 1,
  "userId": 1,
  "scholarshipId": 1,
  "programId": 1,
  "status": "ACCEPTED"
}
```

### DELETE - DELETE `/api/v1/applicants/{id}`

**Example Request:**

```
DELETE /api/v1/applicants/1
Authorization: Bearer <accessToken>
```

**Response (204 No Content):**

```
(empty body)
```

---

# 👤 Profile API

Base:

```
/api/v1/profile
```

Requires: Bearer Token

| Method | Endpoint      | Description         |
| ------ | ------------- | ------------------- |
| GET    | `/`           | List all profiles   |
| PUT    | `/users/{id}` | Update user profile |
| DELETE | `/users/{id}` | Delete user profile |

### READ - GET `/api/v1/profile` (List All Profiles)

**Example Request:**

```
GET /api/v1/profile
Authorization: Bearer <accessToken>
```

**Response (200 OK):**

```json
[
  {
    "id": 1,
    "userId": 1,
    "firstname": "John",
    "lastname": "Doe",
    "phone": "+1234567890",
    "imageUrl": "https://cloudinary.com/image.jpg"
  },
  {
    "id": 2,
    "userId": 2,
    "firstname": "Jane",
    "lastname": "Smith",
    "phone": "+9876543210",
    "imageUrl": "https://cloudinary.com/image2.jpg"
  }
]
```

### UPDATE - PUT `/api/v1/profile/users/{userId}`

**Content-Type:** `multipart/form-data`

**Form Fields:**

- `firstname` (text, required)
- `lastname` (text, required)
- `phone` (text, required)
- `image` (file, optional)

**Example Form Data:**

```
firstname: "John"
lastname: "Doe"
phone: "+1234567890"
image: [file]
```

**Response (200 OK):**

```json
{
  "message": "Profile updated",
  "user": {
    "id": 1,
    "firstname": "John",
    "lastname": "Doe",
    "phone": "+1234567890",
    "imageUrl": "https://cloudinary.com/new-image.jpg"
  }
}
```

### DELETE - DELETE `/api/v1/profile/users/{userId}`

**Example Request:**

```
DELETE /api/v1/profile/users/1
Authorization: Bearer <accessToken>
```

**Response (200 OK):**

```json
{
  "message": "Profile deleted"
}
```

---

# 👥 User Management API

Base:

```
/api/v1/users
```

Requires: Bearer Token

| Method | Endpoint       | Description        |
| ------ | -------------- | ------------------ |
| PATCH  | `/{id}/status` | Update user status |

### UPDATE - PATCH `/api/v1/users/{id}/status`

**Content-Type:** `application/json`

**Request:**

```json
{
  "status": "ACTIVE"
}
```

**Response (200 OK):**

```json
{
  "message": "User status updated"
}
```

**Status Values:** `ACTIVE`, `INACTIVE`, `SUSPENDED`, `DELETED`

**Example Requests:**

Activate user:

```
PATCH /api/v1/users/1/status
Authorization: Bearer <accessToken>
Content-Type: application/json

{
  "status": "ACTIVE"
}
```

Suspend user:

```
PATCH /api/v1/users/1/status
Authorization: Bearer <accessToken>
Content-Type: application/json

{
  "status": "SUSPENDED"
}
```

---

# 🧪 Testing Flow

1. Register user
2. Login
3. Copy accessToken
4. Add Authorization header
5. Test protected endpoints

Example:

```
Authorization: Bearer your_access_token
```

---

# 📅 Date Format

```
YYYY-MM-DDTHH:mm:ss
```

Example:

```
2026-12-31T23:59:59
```

---

# ✅ Notes

• All protected endpoints require Bearer Token
• Use refreshToken when accessToken expires
• File uploads use multipart/form-data
• Use HTTPS in production

---

# 👨‍💻 Maintained by Next Step Edu
