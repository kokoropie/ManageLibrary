CREATE TABLE `tacGia` (
  `id` integer PRIMARY KEY AUTOINCREMENT,
  `ten` varchar(255),
  `ngaySinh` date
);

CREATE TABLE `sach` (
  `id` integer PRIMARY KEY AUTOINCREMENT,
  `ten` varchar(255),
  `tacGia` int,
  `ngayXuatBan` date,
  `ngayNhap` date
);

CREATE TABLE `muonSach` (
  `id` integer PRIMARY KEY AUTOINCREMENT,
  `thanhVien` integer,
  `sach` integer,
  `ngayMuon` datetime,
  `ngayTra` datetime
);

CREATE TABLE `thanhVien` (
  `id` integer PRIMARY KEY AUTOINCREMENT,
  `ten` varchar(255),
  `ngayThamGia` datetime
);

ALTER TABLE `sach` ADD `ref_tacGia` KEY `tacGia` REFERENCES `tacGia` (`id`);

ALTER TABLE `muonSach` ADD `ref_thanhVien` KEY `thanhVien` REFERENCES `thanhVien` (`id`);

ALTER TABLE `muonSach` ADD `ref_sach` KEY `sach` REFERENCES `sach` (`id`);
