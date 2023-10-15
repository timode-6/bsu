<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Default.aspx.cs" Inherits="_Default" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Very good site!</title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
            <br/>
            Здравствуй, мир!    
            <br/>
            <asp:Label ID="Label1" runat="server" Text="Author: Alekceychick Timofey"
                ForeColor="#33CC33" Font-Size="20" Font-Underline="true"></asp:Label>
            <br/>
            <asp:Label ID="Label2" runat="server" Text="Время открытия страницы:"></asp:Label>
            <asp:TextBox ID="Time" runat="server" Text=""></asp:TextBox>
        </div>
    </form>
</body>
</html>
