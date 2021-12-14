<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AudioFile</title>
</head>
<body>
<h1>Search for songs you want</h1>
<form action=searchBySongId.do method="GET">
 	Song ID:
 	<input type="text" name="songId"/>
 	<input type="submit" value="Get Song Data " />
 </form>
<form action=searchBySongName.do method="GET">
 	Song Name:
 	<input type="text" name="songName"/>
 	<input type="submit" value="Get Song Data " />
 </form>
<form action=searchByAlbumName.do method="GET">
 	Album Name:
 	<input type="text" name="songAlbumName"/>
 	<input type="submit" value="Get Song Data " />
 </form>
<form action=searchByLyricsKeywords.do method="GET">
 	Lyrics Keyword:
 	<input type="text" name="LyricsKey"/>
 	<input type="submit" value="Get Song Data " />
 </form>
<form action=searchByArtistName.do method="GET">
 	Artist Name:
 	<input type="text" name="SongArtistName"/>
 	<input type="submit" value="Get Song Data " />
 </form>
<form action=searchBySongRating.do method="GET">
 	Song Rating:
 	<input type="text" name="SongRating"/>
 	<input type="submit" value="Get Song Data " />
 </form>
<form action=sortBySongRating.do method="GET">
 	Sort Rating:
 	<input type="submit" value="Get Sorting Data " />
 </form>
<form action=sortByCreateTime.do method="GET">
 	Sort Create Time:
 	<input type="submit" value="Get Sorting Data " />
 </form>
 
<h1>Search for albums you want</h1>
<form action=searchByAlbumId.do method="GET">
 	Album ID:
 	<input type="text" name="AlbumId"/>
 	<input type="submit" value="Get Album Data " />
 </form>
<form action=searchAlbumByAlbumName.do method="GET">
 	Album Name:
 	<input type="text" name="AlbumName"/>
 	<input type="submit" value="Get Album Data " />
 </form>
<form action=searchAlbumBySongName.do method="GET">
 	Song Name:
 	<input type="text" name="AlbumSongName"/>
 	<input type="submit" value="Get Album Data " />
 </form>
<form action=searchAlbumByArtistName.do method="GET">
 	Artist Name:
 	<input type="text" name="AlbumArtistName"/>
 	<input type="submit" value="Get Album Data " />
 </form>
<form action=searchAlbumByCreatedUsername.do method="GET">
 	Created User Name:
 	<input type="text" name="AlbumUserName"/>
 	<input type="submit" value="Get Album Data " />
 </form>
<form action=searchAlbumByGenre.do method="GET">
 	Genre :
 	<input type="text" name="AlbumGenre"/>
 	<input type="submit" value="Get Album Data " />
 </form>
 
<form action=searchAlbumByCreateTime.do method="GET">
 	Create Time:
 	<input type="text" name="AlbumCreateTime"/>
 	<input type="submit" value="Get Album Data " />
 </form>
<form action=sorthAlbumByRating.do method="GET">
 	Rating :
 	<input type="text" name=ascendingOrder/>
 	<input type="submit" value="Get Album Data " />
 </form>
 
 
<h1>Search for Artist you want</h1>
<form action=searchArtistByArtistId.do method="GET">
 	Artist ID:
 	<input type="text" name="ArtistId"/>
 	<input type="submit" value="Get Artist Data " />
 </form>
<form action=searchArtistByArtistName.do method="GET">
 	Artist Name:
 	<input type="text" name="ArtistName"/>
 	<input type="submit" value=" Get Artist Data" />
 </form>
<form action=searchArtistBySongName.do method="GET">
 	Song Name:
 	<input type="text" name="ArtistSongName"/>
 	<input type="submit" value=" Get Artist Data" />
 </form>
<form action=searchArtistByAlbumName.do method="GET">
 	Album Name:
 	<input type="text" name="ArtistAlbumName"/>
 	<input type="submit" value=" Get Artist Data" />
 </form>
</body>
</html>