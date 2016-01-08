@echo off
set jar=build\libs\Chucken-1.8.9-1.00.jar

set dest2=P:\Minecraft\ForgeServer\Forge189\mods
set dest3=C:\Users\birch\AppData\Roaming\.minecraft\mods

if exist %jar% del %jar%
call gradlew build
if not exist %jar% goto err

echo.
echo Dest2: %dest2%
echo Dest3: %dest3%
echo.
copy %jar% %dest2%
copy %jar% %dest3%

goto end
:err
echo Error
:end

set jar=
set dest2=
set dest3=
