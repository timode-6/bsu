<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Default6_1.aspx.cs" 
    Inherits="Default6_1" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
            <asp:TextBox ID="Number1TB" runat="server"/>
            <asp:TextBox ID="Number2TB" runat="server"/>
            <asp:Button ID="Button1" runat="server" Text="Multiply" OnClick="Button1_Click"/>
        </div>
    </form>
</body>
</html>
