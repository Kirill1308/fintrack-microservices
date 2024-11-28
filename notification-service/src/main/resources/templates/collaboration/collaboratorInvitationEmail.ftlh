<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Invitation to Collaborate</title>
    <style>
        body.email-body {
            font-family: Arial, Helvetica, sans-serif;
        }

        .email-container {
            max-width: 600px;
            margin: 20px auto;
            background-color: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .email-header {
            text-align: center;
            padding-bottom: 20px;
            border-bottom: 2px solid #f0f0f0;
            margin-bottom: 30px;
        }

        .email-title {
            color: #2c3e50;
            font-size: 24px;
            margin: 0;
        }

        .email-content {
            margin-bottom: 30px;
        }

        .email-text {
            margin: 0 0 20px 0;
            font-size: 16px;
            color: #333333 !important;
        }

        .email-button {
            display: inline-block;
            padding: 12px 24px;
            background-color: #3498db;
            color: #ffffff !important;
            text-decoration: none;
            border-radius: 4px;
            margin: 20px 0;
            font-weight: bold;
            text-align: center;
        }

        .email-footer {
            margin-top: 30px;
            padding-top: 20px;
            border-top: 2px solid #f0f0f0;
            font-size: 14px;
            color: #666666;
        }

        .email-contact {
            margin: 0;
        }
    </style>
</head>
<body class="email-body">
<div class="email-container">
    <div class="email-header">
        <h1 class="email-title">Invitation to Collaborate</h1>
    </div>

    <div class="email-content">
        <p class="email-text">
            ${senderEmail} invites you to collaborate on the board.
        </p>

        <a href="${acceptLink}" class="email-button">Accept Invitation</a>
    </div>

    <div class="email-footer">
        <p class="email-contact">
            If you didn't expect this invitation, you can safely ignore this email.
        </p>
    </div>
</div>
</body>
</html>
