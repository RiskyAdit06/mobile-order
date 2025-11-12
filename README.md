# Spring Boot REST API untuk Sistem Pemesanan Seluler Sederhana

## Deskripsi

Aplikasi backend berupa REST API menggunakan Spring Boot untuk sistem pemesanan sederhana.  
API mendukung fitur CRUD produk, menambahkan produk ke keranjang pesanan, dan menyimpan/finalisasi pemesanan.  

---

## Fitur Utama

1. **CRUD Produk**   
   - Menambahkan, mengedit, menghapus, dan melihat daftar produk  
   - Produk terdiri dari nama, tipe (misal Laptop, Book), dan harga

2. **Manajemen Keranjang Pesanan (Order Cart)**  
   - Menambahkan produk ke keranjang dengan quantity tertentu  
   - Menampilkan daftar produk dalam keranjang termasuk harga satuan, quantity, dan total per item  
   - Total harga order dihitung otomatis oleh backend

3. **Order API**  
   - Menyimpan data pesanan (customer, alamat, item order) ke database  
   - Mengambil daftar pesanan yang sudah dibuat

---

## Struktur API

- `GET /api/products` : List produk (dengan pagination)  
- `POST /api/products` : Tambah produk baru  
- `PUT /api/products/{id}` : Update produk  
- `DELETE /api/products/{id}` : Hapus produk  

- `POST /api/orders` : Simpan order baru beserta item, backend hitung total otomatis  
- `GET /api/orders` : List semua order yang sudah dibuat  

---

## Contoh Payload Request

**Tambah Produk Baru**

```json
{
  "name": "Macbook Pro",
  "price": 15000000,
  "typeId": 1
}
```

**Order Produk**
```json
{
  "customer": "TomJerry",
  "address": "Jl. Tali 7 No.9, Jakarta Barat",
  "items": [
    { "productId": 3, "quantity": 1 },
    { "productId": 4, "quantity": 2 }
  ]
}
```

## Download Postman Collection
Untuk memudahkan pengujian API, silakan download Postman collection berikut dan import ke Postman Anda:

Mobile Place Order.postman_collection.json

## Cara Menjalankan Proyek
Jalankan MySQL dan phpMyAdmin di Laragon
Sesuaikan setting database di application.properties
Jalankan aplikasi menggunakan Maven:

```./mvnw spring-boot:run```
