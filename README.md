## 書籍管理API - bookblog

## このアプリについて

- コーディング学習のために作成した書籍管理APIアプリ
- 自分が読んだ技術書を管理するアプリを作れば、コンテンツもアプリもポートフォリオとして使えると思い作成


## 技術スタック

| 種別 | 技術 |
|------|------|
| 言語 | Java 21 |
| フレームワーク | Spring Boot 4.0.5 |
| ORM | Spring Data JPA / Hibernate |
| DB | PostgreSQL 17.9 |
| 認証 | Spring Security / JWT |
| ビルドツール | Maven |
| CI | GitHub Actions |
| コンテナ | Docker / Docker Compose |

## 主な機能

- 技術書のCRUD操作（登録・一覧取得・詳細取得・更新・削除）
- JWTを使ったトークンベース認証
- BCryptによるパスワードハッシュ化
- ステータス管理（積読 / 読書中 / 既読）

## APIエンドポイント

| メソッド | URL | 説明 | 認証 |
|----------|-----|------|------|
| POST | /api/auth/login | ログイン（JWTトークン取得） | 不要 |
| GET | /api/books | 書籍一覧取得 | 必要 |
| GET | /api/books/{id} | 書籍1件取得 | 必要 |
| POST | /api/books | 書籍登録 | 必要 |
| PUT | /api/books/{id} | 書籍更新 | 必要 |
| DELETE | /api/books/{id} | 書籍削除 | 必要 |

## 自分ルール

- バイブコーディング禁止
- 不明点は基本的にインターネットで検索する</br>（課題に対して自分の頭で考える時間を増やすため。SEとしての業務経験からトライ&エラーの過程が自分の血肉となると知っているので。関連情報にも触れる機会になるため）
- 生成AIに聞くのもOK。ただし出力されたコードのコピペはNG
- 今(2026/4)の自分のスキル感では完全に0から開発は難しい、かつ学習効率が悪いと判断し、成果物イメージを伝えたClaude(Sonnet4.6)に作成してもらったWBSをインプットに作業した。
- 実際のWBSは以下。</br>
https://docs.google.com/spreadsheets/d/1PlE-uX0UGPcoRVLaTrBPaoZDxgI2JEBiQIkO4o8dWPs/edit?usp=sharing
- またWBSを生成したコンテキストを持つチャットにPM役、教育係を依頼。
